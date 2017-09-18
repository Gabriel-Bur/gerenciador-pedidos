package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.infnet.android.gerenciador_pedidos.PopUp_Files.MesaPopup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import Classes.Mesa;

public class MesaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    final private DatabaseReference referenciaMesas = mDatabase.child("mesas");


    private Button editarBtn;
    private Button adicionarBtn;
    private ListView listaMesas;
    private ArrayList<Mesa> mesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);
        init();


    }

    private void init() {
        adicionarBtn = (Button) findViewById(R.id.mesa_adicionar);
        editarBtn = (Button) findViewById(R.id.mesa_editarBtn);
        listaMesas = (ListView) findViewById(R.id.mesa_lista);
        listaMesas.setOnItemClickListener(this);


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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(MesaActivity.this,MesaPopup.class));
    }





}