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
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonCadastro;
    private TextView textViewForgotPassword;
    private FirebaseAuth fbAuth;

    private static WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbAuth = FirebaseAuth.getInstance();
        Atributos();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), LoginActivity::onApplyWindowInsets);

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EsqueceuSenha.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChecarDados();
            }
        });

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParaCadastrar();
            }
        });
    }

    private void Atributos() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextSenha);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonCadastro = findViewById(R.id.buttonCadastro);
        textViewForgotPassword = findViewById(R.id.ESSView);
    }

    private void ChecarDados() {
        String email = editTextEmail.getText().toString();
        String senha = editTextPassword.getText().toString();

        if (!TextUtils.isEmpty(email)) {
            if (!TextUtils.isEmpty(senha)) {
                Usuario user = new Usuario();
                user.setemail(email);
                user.setSenha(senha);

                login(user);
            } else {
                Toast.makeText(LoginActivity.this, "Insira sua senha", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Insira seu e-mail", Toast.LENGTH_SHORT).show();
        }
    }

    private void login(Usuario user) {
        fbAuth.signInWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    ParaTelaInicial();
                } else {
                    String error;
                    try {
                        throw Objects.requireNonNull(task.getException());
                    } catch (FirebaseAuthInvalidUserException e) {
                        error = "Esse usuário não existe!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        error = "O e-mail ou a senha inserida está incorreto!";
                    } catch (Exception e) {
                        error = "Erro ao efetuar o login: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ParaCadastrar() {
        Intent intent = new Intent(LoginActivity.this, Cadastro.class);
        startActivity(intent);
    }

    private void ParaTelaInicial() {
        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
