package com.infnet.android.gerenciador_pedidos.PopUp_Files;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.infnet.android.gerenciador_pedidos.CardapioActivity;
import com.infnet.android.gerenciador_pedidos.EncerrarActivity;
import com.infnet.android.gerenciador_pedidos.R;

import Classes.Mesa;

/**
 * Created by Gabriel on 07/09/2017.
 */

public class MesaPopup extends AppCompatActivity {

    private Button addPedido;
    private Button encerrarMesa;
    private Mesa mesaEscolhida;
    private Intent it;

    protected void sizeViewDefinition() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int heigth = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (heigth * 0.6));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesapopup);
        sizeViewDefinition();
        init();


    }

    private void init() {

        addPedido = (Button) findViewById(R.id.mesapopup_addPedidoBtn);
        encerrarMesa = (Button) findViewById(R.id.mesapopup_encerrarBtnBtn);

        // Mesa escolhida para usar na Activity seguinte.
        it = getIntent();
        mesaEscolhida = (Mesa) it.getSerializableExtra("mesaEscolhida");


        encerrarMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MesaPopup.this, EncerrarActivity.class);
                intent.putExtra("mesaEscolhida", mesaEscolhida);
                startActivity(intent);

                //startActivity(new Intent(MesaPopup.this,EncerrarActivity.class));
            }
        });
        addPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MesaPopup.this, CardapioActivity.class);
                intent.putExtra("mesaEscolhida", mesaEscolhida);
                startActivity(intent);

            }
        });


    }

}

