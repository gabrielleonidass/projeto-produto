package com.example.atvpont;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class Produtos extends AppCompatActivity {

    private ProdutoAdapter produtoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        SearchView searchView = findViewById(R.id.search_view);

        List<Produto> listaProdutos = new ArrayList<>();
        // Dados fictícios dos produtos
        listaProdutos.add(new Produto("Camiseta", "Camiseta masculina G", "R$100,00"));
        listaProdutos.add(new Produto("Boné", "Boné preto ajustável", "R$50,00"));
        listaProdutos.add(new Produto("Tênis", "Tênis de corrida", "R$300,00"));
        listaProdutos.add(new Produto("Calça", "Calça jeans 38", "R$110,00"));
        listaProdutos.add(new Produto("Moletom", "Moletom infantil M", "R$150,00"));
        listaProdutos.add(new Produto("Corrente", "Corrente masculina prata", "R$30,00"));

        produtoAdapter = new ProdutoAdapter(listaProdutos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(produtoAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                produtoAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                produtoAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}