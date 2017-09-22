package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Classes.Item;

public class PedidoActivity extends AppCompatActivity {

    final private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private TextView mesaNum;
    private ListView pedidoList;
    private TextView dinheiro;
    private Button enviarComanda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);



    }

    private void init(){
        mesaNum = (TextView) findViewById(R.id.pedido_mesaNumLabel);
        pedidoList = (ListView) findViewById(R.id.pedido_ListView);
        dinheiro = (TextView) findViewById(R.id.pedido_valorTextLabel);
        enviarComanda = (Button) findViewById(R.id.pedido_enviarComandaBtn);





    }

}
