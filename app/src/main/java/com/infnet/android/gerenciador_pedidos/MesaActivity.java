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
import com.google.firebase.database.ValueEventListener;
import com.infnet.android.gerenciador_pedidos.PopUp_Files.MesaPopup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import Classes.Mesa;

public class MesaActivity extends AppCompatActivity{

    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    final private DatabaseReference referenciaMesas = mDatabase.child("mesas");


    private Button editarBtn;
    private Button adicionarBtn;
    private ListView listaMesas;

    private ArrayAdapter<String> adapter;
    private List<String> mesas = new ArrayList<String>();

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


        referenciaMesas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    String mesa = child.getKey().toUpperCase();
                    mesas.add(mesa);
                    adapter.notifyDataSetChanged();
                    Log.i("123",mesa.toString());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listaMesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MesaActivity.this,MesaPopup.class));
            }
        });


        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mesas);
        listaMesas.setAdapter(adapter);



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

    }

}