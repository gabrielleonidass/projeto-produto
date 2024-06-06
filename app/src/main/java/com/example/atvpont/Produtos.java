package com.example.atvpont;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class Produtos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProdutoAdapter produtoAdapter;
    private List<Produto> listaProdutos;
    private EditText barraPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        recyclerView = findViewById(R.id.recycler_view);
        barraPesquisa = findViewById(R.id.search_view);


        listaProdutos = new ArrayList<>();
        listaProdutos.add(new Produto("Camiseta", "Camiseta masculina G", 100, R.drawable.ic_placeholder));
        listaProdutos.add(new Produto("Boné", "Boné preto ajustável", 50, R.drawable.bone));
        listaProdutos.add(new Produto("Tênis", "Tênis de corrida", 300, R.drawable.tenis));
        listaProdutos.add(new Produto("Calça", "Calça jeans 38", 110, R.drawable.calca));
        listaProdutos.add(new Produto("Moletom", "Moletom infantil M", 150, R.drawable.moletom));
        listaProdutos.add(new Produto("Corrente", "Corrente masculina prata", 30, R.drawable.corrente));
        produtoAdapter = new ProdutoAdapter(listaProdutos);
        recyclerView.setAdapter(produtoAdapter);


        produtoAdapter = new ProdutoAdapter(listaProdutos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(produtoAdapter);

        barraPesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                produtoAdapter.filtrar(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
}