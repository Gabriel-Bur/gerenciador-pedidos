package com.infnet.android.gerenciador_pedidos.PopUp_Files;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.infnet.android.gerenciador_pedidos.EditActivity;
import com.infnet.android.gerenciador_pedidos.R;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.UUID;

import Classes.Item;
import Classes.Mesa;

/**
 * Created by Gabriel on 07/09/2017.
 */

public class ObsPopup extends AppCompatActivity {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference referenciaMesa = mDatabase.child("mesas");

    private TextView nomeProduto;
    private TextView descricaoProduto;
    private EditText obsProduto;
    private TextView produtoQuantidade;
    private TextView precoUnitarioProduto;
    private ImageView produtoRemove;
    private ImageView produtoAdd;
    private TextView precoTotalProduto;
    private Button botaoParaAdicionar;

    private Item itemEscolhido;
    private Item itemParaAdicionar;
    private Mesa mesaEscolhida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obspopup);
        init();

    }

    private void init() {

        nomeProduto = (TextView) findViewById(R.id.obs_produtoNome);
        descricaoProduto = (TextView) findViewById(R.id.obs_decription);
        obsProduto = (EditText) findViewById(R.id.obs_obs);
        precoUnitarioProduto = (TextView) findViewById(R.id.obs_unitPriceValue);
        produtoQuantidade = (TextView) findViewById(R.id.obs_produtoQuantidade);
        produtoRemove = (ImageView) findViewById(R.id.obs_produtoRemove);
        produtoAdd = (ImageView) findViewById(R.id.obs_produtoAdd);
        precoTotalProduto = (TextView) findViewById(R.id.obs_TotalTextValue);
        botaoParaAdicionar = (Button) findViewById(R.id.obs_adicionar);

        Intent it = getIntent();
        // joga na tela as informações do produto escolhido
        itemEscolhido = (Item)it.getSerializableExtra("itemEscolhido");
        mesaEscolhida = (Mesa)it.getSerializableExtra("mesaEscolhida");
        nomeProduto.setText(itemEscolhido.getNome());
        descricaoProduto.setText(itemEscolhido.getDescricao());
        precoUnitarioProduto.setText(String.format(Locale.ENGLISH,"%.2f",itemEscolhido.getValor()));
        produtoQuantidade.setText("1");
        precoTotalProduto.setText(CalculoTotal());




        //adiciona do produto quantidade +1
        produtoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(produtoQuantidade.getText().toString()) >= 99) {
                    produtoQuantidade.setText("99");
                } else {
                    int i = Integer.parseInt(produtoQuantidade.getText().toString());
                    i = i + 1;
                    produtoQuantidade.setText(String.valueOf(i));
                    precoTotalProduto.setText(CalculoTotal());
                }
            }
        });
        //Remove do produto quantidade -1
        produtoRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(produtoQuantidade.getText().toString()) <= 1) {
                    produtoQuantidade.setText("1");
                } else {
                    int i = Integer.parseInt(produtoQuantidade.getText().toString());
                    i = i - 1;
                    produtoQuantidade.setText(String.valueOf(i));
                    precoTotalProduto.setText(CalculoTotal());
                }
            }
        });

        botaoParaAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemParaAdicionar = new Item();
                itemParaAdicionar.setItemId(UUID.randomUUID().toString());
                itemParaAdicionar.setNome(itemEscolhido.getNome());
                itemParaAdicionar.setDescricao(itemEscolhido.getDescricao());
                itemParaAdicionar.setCategoria(itemEscolhido.getCategoria());
                itemParaAdicionar.setQuantidade(Integer.parseInt(produtoQuantidade.getText().toString()));
                itemParaAdicionar.setValor(Double.parseDouble(precoUnitarioProduto.getText().toString()));
                itemParaAdicionar.setObs(obsProduto.getText().toString());

                referenciaMesa.child(mesaEscolhida.getNome().toString()).child("pedido").child(itemParaAdicionar.getItemId()).setValue(itemParaAdicionar);
               // referenciaMesa.child(mesaEscolhida.getNome().toString()).child("conta").child(itemParaAdicionar.getItemId()).setValue(itemParaAdicionar);
                finish();
            }
        });




    }

    private String CalculoTotal() {
        Double valor = itemEscolhido.getValor();
        int quantidade = Integer.parseInt(produtoQuantidade.getText().toString());
        double valorTotal = (quantidade * valor);

        return String.format(Locale.ENGLISH,"%.2f",valorTotal);
    }

}
