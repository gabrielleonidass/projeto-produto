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

    private EditText campoEmail, camposSenha; // Campos de entrada para email e senha
    private Button botaoAcesso, buttonCadastro; // Botões de login e cadastro
    private TextView textViewForgotPassword; // Texto para recuperar senha
    private FirebaseAuth fbAuth; // Instância do FirebaseAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Define o layout da atividade

        fbAuth = FirebaseAuth.getInstance(); // Obtém a instância do FirebaseAuth
        inicializarComponente(); // Inicializa os componentes da interface

        // Ajusta o padding para suportar áreas de recorte da tela
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Inicializa os componentes da interface
    private void inicializarComponente() {
        campoEmail = findViewById(R.id.editTextEmail);
        camposSenha = findViewById(R.id.editTextSenha);
        botaoAcesso = findViewById(R.id.buttonLogin);
        buttonCadastro = findViewById(R.id.buttonCadastro);
        textViewForgotPassword = findViewById(R.id.ESSView);
    }

    // Método para validar a autenticação do usuário
    public void validarAutenticacao(View view) {
        String email = campoEmail.getText().toString();
        String senha = camposSenha.getText().toString();

        if (!TextUtils.isEmpty(email)) { // Verifica se o campo email não está vazio
            if (!TextUtils.isEmpty(senha)) { // Verifica se o campo senha não está vazio
                Usuario usuario = new Usuario();
                usuario.setemail(email); // Define o email do usuário
                usuario.setSenha(senha); // Define a senha do usuário

                login(usuario); // Chama o método para logar o usuário
            } else {
                Toast.makeText(this, "Preencher a senha", Toast.LENGTH_SHORT).show(); // Exibe mensagem se o campo senha estiver vazio
            }
        } else {
            Toast.makeText(this, "Preencha o e-mail", Toast.LENGTH_SHORT).show(); // Exibe mensagem se o campo email estiver vazio
        }
    }

    // Método para logar o usuário utilizando Firebase Authentication
    private void login(Usuario usuario) {
        fbAuth.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirHome(); // Se o login for bem-sucedido, abre a tela principal
                } else {
                    String excecao = "";
                    try {
                        throw Objects.requireNonNull(task.getException());
                    } catch (FirebaseAuthInvalidUserException e) {
                        excecao = "Usuário não está cadastrado"; // Captura exceção para usuário não cadastrado
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Email ou senha incorreto"; // Captura exceção para credenciais incorretas
                    } catch (Exception e) {
                        excecao = "Erro ao logar: " + e.getMessage(); // Captura outras exceções
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show(); // Exibe a mensagem de exceção
                }
            }
        });
    }

    // Método para abrir a tela principal
    private void abrirHome() {
        Intent i = new Intent(LoginActivity.this, Produtos.class);
        startActivity(i);
    }

    // Método para abrir a tela de cadastro
    public void ParaCadastrar(View view) {
        Intent intent = new Intent(LoginActivity.this, Cadastro.class);
        startActivity(intent);
    }

    // Método para abrir a tela de "Esqueceu a senha"
    public void esqueceuSenha(View view) {
        Intent intent = new Intent(LoginActivity.this, EsqueceuSenha.class);
        startActivity(intent);
    }
}
