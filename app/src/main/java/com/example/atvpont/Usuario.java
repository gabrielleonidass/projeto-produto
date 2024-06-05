package com.example.atvpont;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Usuario {
    private String nome, email, Senha, telefone;

    public Usuario(String nome, String email, String Senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.Senha = Senha;
        this.telefone = telefone;
    }

    public Usuario() {

    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getSenha() {

        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String gettelefone() {

        return telefone;
    }

    public void settelefone(String telefone) {
        this.telefone = telefone;
    }
}