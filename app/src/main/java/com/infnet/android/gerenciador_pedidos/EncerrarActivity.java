package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import org.w3c.dom.Text;

import Classes.Mesa;


/**
 * Created by Gabriel on 08/09/2017.
 */

public class EncerrarActivity extends AppCompatActivity {

    private Mesa mesaEscolhida;
    private TextView mesaNum;
    private ListView listaTotalItens;
    private TextView valorSubTotal;
    private TextView valorTotal;
    private Button finalizaPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encerrar);
        init();

    }

    private void init() {
        mesaNum = (TextView)findViewById(R.id.encerrar_mesaNumLabel);
        listaTotalItens = (ListView)findViewById(R.id.encerrar_ListView);
        valorSubTotal = (TextView)findViewById(R.id.encerrar_valorSubtotalTextView);
        valorTotal = (TextView)findViewById(R.id.encerrar_valorTotalTextView);
        finalizaPagamento = (Button)findViewById(R.id.encerrar_pagamentoBtn);

        Intent it = getIntent();
        Mesa mesaEscolhida = (Mesa)it.getSerializableExtra("mesaEscolhida");

        mesaNum.setText(mesaEscolhida.getNome().toString().toUpperCase());

    }


}
