package com.example.atvpont;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atvpont.LoginActivity;
import com.example.atvpont.R;
import com.example.atvpont.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private EditText editTextNome, editTextEmail, editTextTelefone, editTextSenha;
    private Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextSenha = findViewById(R.id.editTextSenha);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);



        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos(v);
            }
        });
    }

    public void validarCampos(View view) {
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String telefone = editTextTelefone.getText().toString();
        String senha = editTextSenha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {

                    usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setemail(email);
                    usuario.settelefone(telefone);
                    usuario.setSenha(senha);


                    cadastrarUsuario();

                } else {
                    Toast.makeText(this, "Preencha a senha", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarUsuario() {
        autenticacao = FirebaseAuth.getInstance();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Cadastro.this, "Sucesso ao Cadastrar o usuário", Toast.LENGTH_SHORT).show();
                    Logar();
                } else {
                    Toast.makeText(Cadastro.this, "Falha ao cadastrar o usuário: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); // Exibe mensagem de erro
                }
            }
        });
    }

    public void Logar() {
        Intent in = new Intent(Cadastro.this, LoginActivity.class);
        startActivity(in);
    }

}
