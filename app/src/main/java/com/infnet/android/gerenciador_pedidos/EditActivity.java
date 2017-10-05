package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Classes.CustomExpandableListView;
import Classes.Item;

public class EditActivity extends AppCompatActivity {

    //Referencia do Banco Firebase
    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    final private DatabaseReference referenciaToRemove = mDatabase.child("itens");

    //Referencia das categorias de itens
    private DatabaseReference referenciaPratos = mDatabase.child("itens/pratos");
    private DatabaseReference referenciaAperitivos = mDatabase.child("itens/aperitivos");
    private DatabaseReference referenciaSobremesas = mDatabase.child("itens/sobremesas");
    private DatabaseReference referenciaBebidas = mDatabase.child("itens/bebidas");
    private DatabaseReference referenciaBebidasAlcoolicas = mDatabase.child("itens/bebidas alcoolicas");
    private DatabaseReference referenciaVinhos = mDatabase.child("itens/vinhos");


    private Button editarBtn;
    private Button adicionarBtn;
    private ExpandableListView cardapio ;

    private ArrayList<String> listaDataHeader;
    private ArrayList<Item> listaDePratos;
    private ArrayList<Item> listaDeAperitivos;
    private ArrayList<Item> listaDeSobremesas;
    private ArrayList<Item> listaDeBebidas;
    private ArrayList<Item> listaDeBebidasAlcoolicas;
    private ArrayList<Item> listaDeVinhos;
    private ExpandableListAdapter adapter;
    private Map<String,List<Item>> hashMap = new HashMap<>();
    ////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();

    }


    protected void init(){
        editarBtn  = (Button) findViewById(R.id.edit_editarBtn);
        adicionarBtn = (Button) findViewById(R.id.edit_adicionarBtn);
        cardapio = (ExpandableListView) findViewById(R.id.edit_CardapioListView);
        listaDePratos = new ArrayList<>();
        listaDeAperitivos = new ArrayList<>();
        listaDeSobremesas = new ArrayList<>();
        listaDeBebidas = new ArrayList<>();
        listaDeBebidasAlcoolicas = new ArrayList<>();
        listaDeVinhos = new ArrayList<>();

        listaDataHeader = new ArrayList<>();
        listaDataHeader.add("Pratos");
        listaDataHeader.add("Aperitivos");
        listaDataHeader.add("Sobremesas");
        listaDataHeader.add("Bebidas");
        listaDataHeader.add("Bebidas Alcoolicas");
        listaDataHeader.add("Vinhos");


        referenciaPratos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Item item = child.getValue(Item.class);
                    listaDePratos.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        referenciaAperitivos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Item item = child.getValue(Item.class);
                    listaDeAperitivos.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        referenciaSobremesas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Item item = child.getValue(Item.class);
                    listaDeSobremesas.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        referenciaBebidas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Item item = child.getValue(Item.class);
                    listaDeBebidas.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        referenciaBebidasAlcoolicas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Item item = child.getValue(Item.class);
                    listaDeBebidasAlcoolicas.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        referenciaVinhos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Item item = child.getValue(Item.class);
                    listaDeVinhos.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        hashMap.put(listaDataHeader.get(0),listaDePratos);
        hashMap.put(listaDataHeader.get(1),listaDeAperitivos);
        hashMap.put(listaDataHeader.get(2),listaDeSobremesas);
        hashMap.put(listaDataHeader.get(3),listaDeBebidas);
        hashMap.put(listaDataHeader.get(4),listaDeBebidasAlcoolicas);
        hashMap.put(listaDataHeader.get(5),listaDeVinhos);

        adapter = new CustomExpandableListView(this,listaDataHeader,hashMap);
        cardapio.setAdapter(adapter);


        cardapio.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (ExpandableListView.getPackedPositionType(id)==ExpandableListView.PACKED_POSITION_TYPE_CHILD){
                    int groupPosition = ExpandableListView.getPackedPositionGroup(id);
                    int childPosition = ExpandableListView.getPackedPositionChild(id);

                    DatabaseReference toRemove = referenciaToRemove.child(listaDataHeader.get(groupPosition).toLowerCase()) .child(hashMap.get(listaDataHeader.get(groupPosition)).get(childPosition).getItemId());
                    toRemove.removeValue();
                    Toast.makeText(EditActivity.this, "Produto Excluido", Toast.LENGTH_SHORT).show();
                    finish();

                }

                return false;
            }
        });



        adicionarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditActivity.this,FormularioActivity.class));
                finish();
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
                    adicionarBtn.setVisibility(View.INVISIBLE);
                }

            }
        });
    }



}
