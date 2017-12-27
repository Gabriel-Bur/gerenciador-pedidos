package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Classes.Custom.CustomListView;
import Classes.Entity.Item;
import Classes.Entity.Mesa;

public class PedidoActivity extends AppCompatActivity {

    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference referenciaMesa = mDatabase.child("mesas");

    private Intent it;
    private TextView mesaNum;
    private ListView pedidoList;
    private TextView dinheiro;
    private Button enviarComanda;
    private Mesa mesaEscolhida;

    private ListAdapter adapter;
    private List<Item> pedidosDaMesa = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        init();
    }


    private void init(){
        mesaNum = (TextView) findViewById(R.id.pedido_mesaNumLabel);
        pedidoList = (ListView) findViewById(R.id.pedido_ListView);
        dinheiro = (TextView) findViewById(R.id.pedido_valorTextLabel);
        enviarComanda = (Button) findViewById(R.id.pedido_enviarComandaBtn);

        mDatabase.keepSynced(true);

        it = getIntent();
        mesaEscolhida = (Mesa)it.getSerializableExtra("mesaEscolhida");
        mesaNum.setText(mesaEscolhida.getNome().toString().toUpperCase());

        referenciaMesa.child(mesaEscolhida.getNome().toLowerCase()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double valor = 0.0;
                //pega cada item de pedido na mesa escolhida
                try
                {
                    for (DataSnapshot child : dataSnapshot.child("pedido").getChildren()) {
                        Item item = child.getValue(Item.class);
                        pedidosDaMesa.add(item);
                        valor = valor + (item.getValor()*item.getQuantidade());
                    }
                }catch (Exception e){
                }
                String formater = String.format("%.2f",valor);
                dinheiro.setText(formater);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter = new CustomListView(this,android.R.layout.simple_list_item_1,pedidosDaMesa);
        pedidoList.setAdapter(adapter);


        //Limpa a lista do pedido
        enviarComanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!pedidosDaMesa.isEmpty()){
                    DatabaseReference fromPath = referenciaMesa.child(mesaEscolhida.getNome().toLowerCase()).child("pedido");
                    DatabaseReference toPath = referenciaMesa.child(mesaEscolhida.getNome().toLowerCase()).child("conta");
                    moveFirabaseRecord(fromPath,toPath);
                    fromPath.removeValue();

                    pedidosDaMesa.clear();
                    dinheiro.setText("0.00");
                    Toast.makeText(getApplicationContext(),"Pedido Enviado",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Pedido vazio",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    public void moveFirabaseRecord(final DatabaseReference fromPath, final  DatabaseReference toPath){
        fromPath.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    for(DataSnapshot child: dataSnapshot.getChildren()){
                        Item item = child.getValue(Item.class);
                        toPath.child(item.getItemId()).setValue(item);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
