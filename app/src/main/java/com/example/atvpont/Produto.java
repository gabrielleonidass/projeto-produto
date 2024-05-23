package com.example.atvpont;

public class Produto {
    private String nome;
    private String descricao;
    private String preco;
    private int imagemRecurso;

    public Produto(String nome, String descricao, String preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagemRecurso = imagemRecurso;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public int getImagemRecurso() {
        return imagemRecurso;
    }

    public void setImagemRecurso(int imagemRecurso) {
        this.imagemRecurso = imagemRecurso;
    }
}