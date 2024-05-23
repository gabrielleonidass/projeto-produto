package com.example.atvpont;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class Produtos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProdutoAdapter produtoAdapter;
    private List<Produto> listaProdutos;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.search_view);

        listaProdutos = new ArrayList<>();
        // Dados fictícios dos produtos
        listaProdutos.add(new Produto("Produto 1", "Descrição do produto 1", "R$100,00"));
        listaProdutos.add(new Produto("Produto 2", "Descrição do produto 2", "R$200,00"));

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