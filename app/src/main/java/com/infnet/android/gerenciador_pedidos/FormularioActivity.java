package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Classes.Item;
import DAO.ConfiguracaoFirebase;


public class FormularioActivity extends AppCompatActivity implements View.OnClickListener {

    //Referencia do Banco Firebase
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    //Referencia do filho Itens
    private DatabaseReference referenciaItens = mDatabase.child("itens");

    private Spinner categoria;
    private EditText nomeProduto;
    private EditText descricaoProduto;
    private EditText precoProduto;
    private Button adicionarProduto;
    private Item novoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        init();


        adicionarProduto.setOnClickListener(this);
    }

    protected void init(){
        categoria = (Spinner) findViewById(R.id.formulario_spinner);
        nomeProduto = (EditText) findViewById(R.id.formulario_nomeProduto);
        descricaoProduto = (EditText) findViewById(R.id.formulario_descricaoProduto);
        precoProduto = (EditText) findViewById(R.id.formulario_Valor);
        adicionarProduto = (Button) findViewById(R.id.formulario_adicionarBtn);
    }

    @Override
    public void onClick(View view) {
        try {

                String itemId = UUID.randomUUID().toString();
                String nome = nomeProduto.getText().toString();
                String descricao = descricaoProduto.getText().toString();
                Double preco = Double.parseDouble(precoProduto.getText().toString());
                String categoriaEscolhida = categoria.getSelectedItem().toString();

                //Criação do novo Item que será salvo no firebase
                if (!nome.equals("") || !preco.equals(null)) {
                    novoItem = new Item();
                    novoItem.setItemId(itemId);
                    novoItem.setNome(nome);
                    novoItem.setCategoria(categoriaEscolhida);
                    novoItem.setDescricao(descricao);
                    novoItem.setValor(preco);
                }else {
                    Toast.makeText(FormularioActivity.this,"Preencha corretamente",Toast.LENGTH_LONG).show();
                }

            //Salva o Item de Acordo com a categoria escolhida.
            referenciaItens.child(novoItem.getCategoria().toString().toLowerCase()).child(novoItem.getItemId()).setValue(novoItem);

            Toast.makeText(FormularioActivity.this,"Produto Cadastrado", Toast.LENGTH_LONG).show();
            finish();

        }catch (Exception e){
            Toast.makeText(FormularioActivity.this,"Preencha corretamente",Toast.LENGTH_LONG).show();
        }

    }
}
