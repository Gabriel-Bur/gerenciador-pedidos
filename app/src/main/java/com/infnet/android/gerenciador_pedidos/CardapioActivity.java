package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.infnet.android.gerenciador_pedidos.PopUp_Files.ObsPopup;

public class CardapioActivity extends AppCompatActivity {

    private Button pedido;
    private TextView total;
    private ExpandableListView cardapio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        pedido = (Button) findViewById(R.id.cardapio_carrinhoBtn);
        cardapio = (ExpandableListView) findViewById(R.id.cardapio_ListView);


        cardapio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //Escolha do Produto do cardápio
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(CardapioActivity.this,ObsPopup.class));
            }
        });

        pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CardapioActivity.this,PedidoActivity.class));
            }
        });

    }
}
