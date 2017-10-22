package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.infnet.android.gerenciador_pedidos.PopUp_Files.MesaPopup;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.UUID;

import Classes.Mesa;
import Classes.Pedido;

public class MesaActivity extends AppCompatActivity{

    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    final private DatabaseReference referenciaMesas = mDatabase.child("mesas");


    private Button editarBtn;
    private Button adicionarBtn;
    private ListView listaMesas;

    private String ultimaMesa;

    private ArrayAdapter<Mesa> adapter;
    private List<Mesa> mesas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);
        init();



    }

    private void init() {
        adicionarBtn = (Button)findViewById(R.id.mesa_adicionar);
        editarBtn = (Button)findViewById(R.id.mesa_editarBtn);
        listaMesas = (ListView)findViewById(R.id.mesa_lista);



        //Lista todas as mesas do banco
        referenciaMesas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mesas.clear();
                try{
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Mesa mesa = child.getValue(Mesa.class);
                        mesas.add(mesa);
                        adapter.notifyDataSetChanged();
                    }
                }catch (Exception e){}
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mesas);
        listaMesas.setAdapter(adapter);

        //Passa para a proxima Activity a mesa selecionada
        listaMesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //Passa para uma intent a mesa selecionada
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Mesa mesaEscolhida = (Mesa) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(MesaActivity.this,MesaPopup.class);
                intent.putExtra("mesaEscolhida",mesaEscolhida);
                startActivity(intent);

            }
        });

        editarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editarBtn.getText().equals("Editar")){
                    editarBtn.setText("Cancelar");
                    adicionarBtn.setVisibility(View.VISIBLE);
                }else{
                    editarBtn.setText("Editar");
                    adicionarBtn.setVisibility(view.INVISIBLE);
                }
            }
        });

        ultimaMesa();

    }

    public void ultimaMesa(){

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Query lastQuery = database.child("mesas").limitToLast(1);

        lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ultimaMesa = dataSnapshot.getKey().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}