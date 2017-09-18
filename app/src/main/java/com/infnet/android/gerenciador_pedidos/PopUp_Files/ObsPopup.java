package com.infnet.android.gerenciador_pedidos.PopUp_Files;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.infnet.android.gerenciador_pedidos.R;

import org.w3c.dom.Text;

/**
 * Created by Gabriel on 07/09/2017.
 */

public class ObsPopup extends AppCompatActivity {

    private TextView nomeProduto;
    private TextView descricaoProduto;
    private EditText obsProduto;
    private TextView precoUnitarioProduto;
    private ImageView produtoRemove;
    private ImageView produtoAdd;
    private TextView precoTotalProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obspopup);


        nomeProduto = (TextView) findViewById(R.id.obs_produtoNome);
        descricaoProduto = (TextView) findViewById(R.id.obs_decription);
        obsProduto = (EditText) findViewById(R.id.obs_obs);
        precoUnitarioProduto = (TextView) findViewById(R.id.obs_unitPriceValue);
        produtoRemove = (ImageView) findViewById(R.id.obs_produtoRemove);
        produtoAdd = (ImageView) findViewById(R.id.obs_produtoAdd);
        precoTotalProduto = (TextView) findViewById(R.id.obs_TotalTextValue);




    }
}
