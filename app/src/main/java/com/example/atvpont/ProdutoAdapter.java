package com.example.atvpont;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> implements Filterable {

    private final List<Produto> listaProdutos;
    private final List<Produto> listaProdutosCompleta;

    public ProdutoAdapter(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
        listaProdutosCompleta = new ArrayList<>(listaProdutos);
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produtoAtual = listaProdutos.get(position);
        holder.imageView.setImageResource(produtoAtual.getImagemRecurso());
        holder.textViewNome.setText(produtoAtual.getNome());
        holder.textViewDescricao.setText(produtoAtual.getDescricao());
        holder.textViewPreco.setText(produtoAtual.getPreco());
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    @Override
    public Filter getFilter() {
        return produtoFiltro;
    }

    private final Filter produtoFiltro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Produto> listaFiltrada = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                listaFiltrada.addAll(listaProdutosCompleta);
            } else {
                String padraoFiltro = constraint.toString().toLowerCase().trim();
                for (Produto produto : listaProdutosCompleta) {
                    if (produto.getNome().toLowerCase().contains(padraoFiltro)) {
                        listaFiltrada.add(produto);
                    }
                }
            }
            FilterResults resultados = new FilterResults();
            resultados.values = listaFiltrada;
            return resultados;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaProdutos.clear();
            boolean b = listaProdutos.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class ProdutoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewNome;
        TextView textViewDescricao;
        TextView textViewPreco;

        ProdutoViewHolder(View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.nome_produto);
            textViewDescricao = itemView.findViewById(R.id.descricao_produto);
            textViewPreco = itemView.findViewById(R.id.preco_produto);
        }
    }
}