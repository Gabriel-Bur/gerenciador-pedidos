package com.infnet.android.gerenciador_pedidos.PopUp_Files;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.infnet.android.gerenciador_pedidos.EditActivity;
import com.infnet.android.gerenciador_pedidos.R;

import org.w3c.dom.Text;

import Classes.Item;

/**
 * Created by Gabriel on 07/09/2017.
 */

public class ObsPopup extends AppCompatActivity {

    private TextView nomeProduto;
    private TextView descricaoProduto;
    private EditText obsProduto;
    private TextView ProdutoQuantidade;
    private TextView precoUnitarioProduto;
    private ImageView produtoRemove;
    private ImageView produtoAdd;
    private TextView precoTotalProduto;
    private Item itemEscolhido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obspopup);
        init();

    }

    private void init(){

        nomeProduto = (TextView) findViewById(R.id.obs_produtoNome);
        descricaoProduto = (TextView) findViewById(R.id.obs_decription);
        obsProduto = (EditText) findViewById(R.id.obs_obs);
        precoUnitarioProduto = (TextView) findViewById(R.id.obs_unitPriceValue);
        ProdutoQuantidade = (TextView) findViewById(R.id.obs_produtoQuantidade);
        produtoRemove = (ImageView) findViewById(R.id.obs_produtoRemove);
        produtoAdd = (ImageView) findViewById(R.id.obs_produtoAdd);
        precoTotalProduto = (TextView) findViewById(R.id.obs_TotalTextValue);

        Intent it = getIntent();
        itemEscolhido = (Item)it.getSerializableExtra("itemEscolhido");
        nomeProduto.setText(itemEscolhido.getNome());
        descricaoProduto.setText(itemEscolhido.getDescricao());
        precoUnitarioProduto.setText(String.format("%.2f",itemEscolhido.getValor()));
        ProdutoQuantidade.setText("1");
        precoTotalProduto.setText(CalculoTotal());



        produtoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantidade = Integer.parseInt(ProdutoQuantidade.getText().toString());
                if (quantidade >= 1) {
                    quantidade = quantidade + 1;
                    ProdutoQuantidade.setText(quantidade);
                }else
                    ProdutoQuantidade.setText("0");
            }
        });

        produtoRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantidade = Integer.parseInt(ProdutoQuantidade.getText().toString());
                if (quantidade > 1) {
                    quantidade = quantidade - 1;
                    ProdutoQuantidade.setText(quantidade);
                }else{
                    ProdutoQuantidade.setText("1");

                }
            }
        });




    }

    private String CalculoTotal(){
        Double valor = itemEscolhido.getValor();
        int quantidade = Integer.parseInt(ProdutoQuantidade.getText().toString());
        double valorTotal = (quantidade*valor);

        return String.format("%.2f",valorTotal);
    }

}
