package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Classes.Item;
import Classes.Mesa;
import Classes.Pedido;

public class PedidoActivity extends AppCompatActivity {

    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference referenciaMesa = mDatabase.child("mesas");

    private Intent it;
    private TextView mesaNum;
    private ListView pedidoList;
    private TextView dinheiro;
    private Button enviarComanda;
    private Mesa mesaEscolhida;

    private ArrayAdapter<Item> adapter;
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

        it = getIntent();
        mesaEscolhida = (Mesa)it.getSerializableExtra("mesaEscolhida");
        mesaNum.setText(mesaEscolhida.getNome().toString().toUpperCase());

        referenciaMesa.child(mesaEscolhida.getNome()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //pega cada item de pedido na mesa escolhida
                try
                {
                    for (DataSnapshot child : dataSnapshot.child("pedido").getChildren()) {
                        Item item = child.getValue(Item.class);
                        pedidosDaMesa.add(item);
                        adapter.notifyDataSetChanged();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,pedidosDaMesa);
        pedidoList.setAdapter(adapter);

    }

}
