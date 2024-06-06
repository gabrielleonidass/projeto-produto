package com.example.atvpont;

public class Produto {
    private String nome;
    private String descricao;
    private double preco;
    private int imagemResourceId;

    public Produto(String nome, String descricao, double preco, int imagemResourceId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imagemResourceId = imagemResourceId;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public int getImagemResourceId() { return imagemResourceId; }
}
