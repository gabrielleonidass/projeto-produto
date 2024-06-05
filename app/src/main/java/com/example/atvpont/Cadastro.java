package com.example.atvpont;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastro extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPhone, editTextPassword;
    private Button buttonCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextName = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextTelefone);
        editTextPassword = findViewById(R.id.editTextSenha);
        buttonCadastro = findViewById(R.id.buttonCadastrar);

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String password = editTextPassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {
                    Usuario user = new Usuario(name, email, phone, password);
                    Gerente.getInstance().addUser(user);

                    Toast.makeText(Cadastro.this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                    // Limpar campos de texto após o cadastro
                    editTextName.setText("");
                    editTextEmail.setText("");
                    editTextPhone.setText("");
                    editTextPassword.setText("");

                    // Voltar para a tela de login
                    finish();
                } else {
                    Toast.makeText(Cadastro.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
