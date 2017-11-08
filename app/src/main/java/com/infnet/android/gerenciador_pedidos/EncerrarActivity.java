package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Classes.CustomListView;
import Classes.Item;
import Classes.Mesa;


/**
 * Created by Gabriel on 08/09/2017.
 */

public class EncerrarActivity extends AppCompatActivity {


    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference referenciaMesas = mDatabase.child("mesas");


    private Mesa mesaEscolhida;
    private TextView mesaNum;
    private ListView totalList;
    private TextView valorSubTotal;
    private TextView valorTotal;
    private Button finalizaPagamento;

    private ListAdapter adapter;
    private List<Item> pedidosDaMesa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encerrar);
        init();

    }

    private void init() {
        mesaNum = (TextView)findViewById(R.id.encerrar_mesaNumLabel);
        totalList = (ListView)findViewById(R.id.encerrar_ListView);
        valorSubTotal = (TextView)findViewById(R.id.encerrar_valorSubtotalTextView);
        valorTotal = (TextView)findViewById(R.id.encerrar_valorTotalTextView);
        finalizaPagamento = (Button)findViewById(R.id.encerrar_pagamentoBtn);

        mDatabase.keepSynced(true);


        Intent it = getIntent();
        mesaEscolhida = (Mesa)it.getSerializableExtra("mesaEscolhida");
        mesaNum.setText(mesaEscolhida.getNome().toString().toUpperCase());

        referenciaMesas.child(mesaEscolhida.getNome().toLowerCase()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double valor = 0.0;
                //pega cada item de pedido na mesa escolhida
                try
                {
                    for (DataSnapshot child : dataSnapshot.child("conta").getChildren()) {
                        Item item = child.getValue(Item.class);
                        pedidosDaMesa.add(item);
                        valor = valor + (item.getValor()*item.getQuantidade());

                    }
                }catch(Exception e)
                {}
                finally {
                    String formatter = String.format("%.2f",valor);
                    valorSubTotal.setText(formatter);
                    String formatter2 = String.format("%.2f", valor * 1.1);
                    valorTotal.setText(formatter2);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new CustomListView(this,android.R.layout.simple_list_item_1,pedidosDaMesa);
        totalList.setAdapter(adapter);


        finalizaPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    referenciaMesas.child(mesaEscolhida.getNome().toLowerCase()).child("conta").removeValue();
                    pedidosDaMesa.clear();
                    Toast.makeText(getApplicationContext(),"Pagamento efetuado",Toast.LENGTH_LONG).show();
                    finish();
                }catch (Exception e){}
            }
        });
    }


}
