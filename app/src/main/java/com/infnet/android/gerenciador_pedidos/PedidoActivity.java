package com.infnet.android.gerenciador_pedidos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PedidoActivity extends AppCompatActivity {


    private TextView mesaNum ;
    private ListView pedidoList ;
    private TextView dinheiro ;
    private Button enviarComanda ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        mesaNum = (TextView) findViewById(R.id.pedido_mesaNumLabel);
        pedidoList = (ListView) findViewById(R.id.pedido_ListView);
        dinheiro = (TextView) findViewById(R.id.pedido_valorTextLabel);
        enviarComanda = (Button) findViewById(R.id.pedido_enviarComandaBtn);

    }
}
