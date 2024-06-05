package com.example.atvpont;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class EsqueceuSenha extends AppCompatActivity {
    private EditText ESSField;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu_senha);

        Atributos();
        fbAuth = FirebaseAuth.getInstance();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void Atributos() {
        View EsqueceuSenha = findViewById(R.id.ESSView);
    }

    public void MudarSenha(View v) {
        String ESS = ESSField.getText().toString();

        if (!TextUtils.isEmpty(ESS)) {
            fbAuth.sendPasswordResetEmail(ESS).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(EsqueceuSenha.this, "Redefina sua senha pelo e-mail cadastrado", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(EsqueceuSenha.this, LoginActivity.class);
                    startActivity(in);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EsqueceuSenha.this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(EsqueceuSenha.this, "Insira seu e-mail", Toast.LENGTH_SHORT).show();
        }
    }

    public void ParaTelaLogin(View v) {
        Intent in = new Intent(EsqueceuSenha.this, LoginActivity.class);
        startActivity(in);
    }
}
