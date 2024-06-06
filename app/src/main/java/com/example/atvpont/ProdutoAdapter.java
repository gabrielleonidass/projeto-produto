package com.example.atvpont;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {
    private List<Produto> listaProdutos;
    private List<Produto> listaFiltrada;

    public ProdutoAdapter(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
        this.listaFiltrada = new ArrayList<>(listaProdutos);
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = listaFiltrada.get(position);
        holder.nomeTextView.setText(produto.getNome());
        holder.descricaoTextView.setText(produto.getDescricao());
        holder.precoTextView.setText(String.format("R$%.2f", produto.getPreco()));

        // Use Glide para carregar a imagem
        Glide.with(holder.itemView.getContext())
                .load(produto.getImagemResourceId())
                .placeholder(R.drawable.ic_placeholder)
                .into(holder.imagemView);
    }

    @Override
    public int getItemCount() {
        return listaFiltrada.size();
    }

    public void filtrar(String query) {
        listaFiltrada.clear();
        if (query.isEmpty()) {
            listaFiltrada.addAll(listaProdutos);
        } else {
            for (Produto produto : listaProdutos) {
                if (produto.getNome().toLowerCase().contains(query.toLowerCase())) {
                    listaFiltrada.add(produto);
                }
            }
        }
        notifyDataSetChanged();
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        ImageView imagemView;
        TextView nomeTextView;
        TextView descricaoTextView;
        TextView precoTextView;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemView = itemView.findViewById(R.id.imagem_produto);
            nomeTextView = itemView.findViewById(R.id.nome_produto);
            descricaoTextView = itemView.findViewById(R.id.descricao_produto);
            precoTextView = itemView.findViewById(R.id.preco_produto);
        }
    }
}
