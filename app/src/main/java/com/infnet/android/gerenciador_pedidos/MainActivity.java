package com.infnet.android.gerenciador_pedidos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Classes.Usuario;
import DAO.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity{

    private FirebaseAuth autenticacao;
    private FirebaseAuth mAuth;
    CallbackManager mCallbackManager;
    private Usuario usuario;


    private EditText username;
    private EditText password;
    private Button loginBtn;
    private LoginButton loginFacebook;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.signOut();
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        init();


    }

    //Inicia o rastreio dos conponentes de interface
    private void init(){
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

        username = (EditText) findViewById(R.id.main_UserEditText);
        password = (EditText) findViewById(R.id.main_PassEditText);
        loginBtn = (Button) findViewById(R.id.main_LoginBtn);
        loginFacebook = (LoginButton)findViewById(R.id.main_LoginFacebookBtn);
        loginFacebook.setReadPermissions("email","public_profile");

        loginFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        //Salva as informações inputadas em uma instancia de Usuário para ser validado
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().equals("") && !password.getText().toString().equals("")){
                    usuario = new Usuario();
                    usuario.setEmail(username.getText().toString());
                    usuario.setPassword(password.getText().toString());
                    validarLogin(usuario);

                }else{
                    Toast.makeText(MainActivity.this,"Preencha corretamente",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    private void handleFacebookAccessToken(AccessToken token){
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                        }else{

                        }
                    }
                });
    }

    //Valida o Login no Firebase
    private void validarLogin(Usuario user){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(user.getEmail().toString(),user.getPassword().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this,MenuActivity.class));
                }else{
                    Toast.makeText(MainActivity.this,"Email ou senha inválida",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
