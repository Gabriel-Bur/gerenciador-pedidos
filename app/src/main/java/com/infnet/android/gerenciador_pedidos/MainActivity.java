package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Classes.Entity.Usuario;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Usuario usuario;

    private EditText username;
    private EditText password;
    private Button loginBtn;
    private Button registerBtn;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    //Inicia o rastreio dos conponentes de interface
    private void init() {
        mAuth = FirebaseAuth.getInstance();

        username = (EditText) findViewById(R.id.main_UserEditText);
        password = (EditText) findViewById(R.id.main_PassEditText);
        loginBtn = (Button) findViewById(R.id.main_LoginBtn);
        registerBtn = (Button) findViewById(R.id.main_RegistroBtn);

        //inicia tela de registro
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });



        //Salva as informações inputadas em uma instancia de Usuário para ser validado
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().equals("") &&
                        !password.getText().toString().equals("")) {

                    usuario = new Usuario();
                    usuario.setEmail(username.getText().toString());
                    usuario.setPassword(password.getText().toString());
                    validarLogin(usuario);

                } else {
                    Toast.makeText(MainActivity.this, "Preencha corretamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //Valida o Login no FirebaseAuth
    private void validarLogin(Usuario user) {

        mAuth.signInWithEmailAndPassword(user.getEmail().toString(), user.getPassword().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "Email ou senha inválida", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
