package com.example.atvpont;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, camposSenha;
    private Button botaoAcesso, buttonCadastro;
    private TextView textViewForgotPassword;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fbAuth = FirebaseAuth.getInstance();
        inicializarComponente();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void inicializarComponente() {
        campoEmail = findViewById(R.id.editTextEmail);
        camposSenha = findViewById(R.id.editTextSenha);
        botaoAcesso = findViewById(R.id.buttonLogin);
        buttonCadastro = findViewById(R.id.buttonCadastro);
        textViewForgotPassword = findViewById(R.id.ESSView);
    }

    public void validarAutenticacao(View view) {
        String email = campoEmail.getText().toString();
        String senha = camposSenha.getText().toString();

        if (!TextUtils.isEmpty(email)) {
            if (!TextUtils.isEmpty(senha)) {
                Usuario usuario = new Usuario();
                usuario.setemail(email);
                usuario.setSenha(senha);

                login(usuario);
            } else {
                Toast.makeText(this, "Preencher a senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha o e-mail", Toast.LENGTH_SHORT).show();
        }
    }


    private void login(Usuario usuario) {
        fbAuth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirHome();
                } else {
                    String excecao = "";
                    try {
                        throw Objects.requireNonNull(task.getException());
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuário não está cadastrado";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email ou senha incorreto";
                    } catch (Exception e) {
                        excecao = "Erro ao logar: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirHome() {
        Intent i = new Intent(LoginActivity.this, Produtos.class);
        startActivity(i);
    }

    public void ParaCadastrar(View view) {
        Intent intent = new Intent(LoginActivity.this, Cadastro.class);
        startActivity(intent);
    }

    public void esqueceuSenha(View view) {
        Intent intent = new Intent(LoginActivity.this, EsqueceuSenha.class);
        startActivity(intent);
    }
}
