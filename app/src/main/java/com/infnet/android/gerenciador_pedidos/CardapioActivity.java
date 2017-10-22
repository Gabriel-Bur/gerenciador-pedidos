package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.infnet.android.gerenciador_pedidos.PopUp_Files.ObsPopup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Classes.CustomExpandableListView;
import Classes.Item;
import Classes.Mesa;

public class CardapioActivity extends AppCompatActivity {



    //Referencia do Banco Firebase
    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    //Referencia das categorias de itens
    private DatabaseReference referenciaPratos = mDatabase.child("itens/pratos");
    private DatabaseReference referenciaAperitivos = mDatabase.child("itens/aperitivos");
    private DatabaseReference referenciaSobremesas = mDatabase.child("itens/sobremesas");
    private DatabaseReference referenciaBebidas = mDatabase.child("itens/bebidas");
    private DatabaseReference referenciaBebidasAlcoolicas = mDatabase.child("itens/bebidas alcoolicas");
    private DatabaseReference referenciaVinhos = mDatabase.child("itens/vinhos");

    private Button pedido;
    private Mesa mesaEscolhida;



    private ArrayList<String> listaDataHeader;
    private ArrayList<Item> listaDePratos;
    private ArrayList<Item> listaDeAperitivos;
    private ArrayList<Item> listaDeSobremesas;
    private ArrayList<Item> listaDeBebidas;
    private ArrayList<Item> listaDeBebidasAlcoolicas;
    private ArrayList<Item> listaDeVinhos;
    private ExpandableListAdapter adapter;
    private Map<String,List<Item>> hashMap = new HashMap<>();
    private ExpandableListView cardapio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        init();

    }


    private void init(){


        pedido = (Button) findViewById(R.id.cardapio_carrinhoBtn);
        cardapio = (ExpandableListView) findViewById(R.id.cardapio_ListView);
        listaDePratos = new ArrayList<>();
        listaDeAperitivos = new ArrayList<>();
        listaDeSobremesas = new ArrayList<>();
        listaDeBebidas = new ArrayList<>();
        listaDeBebidasAlcoolicas = new ArrayList<>();
        listaDeVinhos = new ArrayList<>();

        //Recebe a mesa escolhida pelo usuário
        final Intent it = getIntent();
        mesaEscolhida = (Mesa)it.getSerializableExtra("mesaEscolhida");


        //As categorias da lista
        listaDataHeader = new ArrayList<>();
        listaDataHeader.add("Pratos");
        listaDataHeader.add("Aperitivos");
        listaDataHeader.add("Sobremesas");
        listaDataHeader.add("Bebidas");
        listaDataHeader.add("Bebidas Alcoolicas");
        listaDataHeader.add("Vinhos");


        //Carrega a lista de cada categoria em um lista
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



        //Cria um dicionario e atribui uma chave com o nome das categorias para cada lista
        hashMap.put(listaDataHeader.get(0),listaDePratos);
        hashMap.put(listaDataHeader.get(1),listaDeAperitivos);
        hashMap.put(listaDataHeader.get(2),listaDeSobremesas);
        hashMap.put(listaDataHeader.get(3),listaDeBebidas);
        hashMap.put(listaDataHeader.get(4),listaDeBebidasAlcoolicas);
        hashMap.put(listaDataHeader.get(5),listaDeVinhos);

        adapter = new CustomExpandableListView(this,listaDataHeader,hashMap);
        cardapio.setAdapter(adapter);



        //Passa para a proxima Activity o Item selecionado
        cardapio.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition ,int childPosition, long id) {

                Item itemEscolhido = (Item)adapter.getChild(groupPosition,childPosition);
                Intent it = new Intent(CardapioActivity.this, ObsPopup.class);
                it.putExtra("itemEscolhido", itemEscolhido);
                it.putExtra("mesaEscolhida", mesaEscolhida);
                startActivity(it);

                return false;
            }
        });


        pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardapioActivity.this,PedidoActivity.class);
                intent.putExtra("mesaEscolhida", mesaEscolhida);
                startActivity(intent);
            }
        });
    }
}
