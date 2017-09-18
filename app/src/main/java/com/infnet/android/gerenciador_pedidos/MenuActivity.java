package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    private TextView nomeRestaurante;
    private Button mesaBtn ;
    private Button cardapioBtn ;
    private Button sobreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mesaBtn = (Button) findViewById(R.id.menu_buttonMesas);
        cardapioBtn = (Button) findViewById(R.id.menu_buttonCardapio);




        cardapioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,EditActivity.class));
            }
        });
        mesaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this,MesaActivity.class));
            }
        });

    }
}
