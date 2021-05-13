package com.example.testeimply;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Dados> dados;
    private Context context;


    public ItemAdapter(List<Dados> dados, Context context) {
        this.dados = dados;
          this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Dados dado = dados.get(position);
        holder.titulo.setText(dado.getdsc_produto());
        holder.imagem.setImageBitmap(dado.getimagem());
        holder.preco.setText(String.format("R$ %.2f", dado.getValor()).replace(".", ","));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Posição: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return dados.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView titulo, preco, textpreco, textqtd;
        private ImageView imagem;
        private int qtd=0;

        ItemViewHolder(View itemView) {
            super(itemView);
            imagem = itemView.findViewById(R.id.imagem);
            titulo = itemView.findViewById(R.id.nomeitem);
            preco = itemView.findViewById(R.id.precoitem);
            textqtd = itemView.findViewById(R.id.items);
            textpreco = itemView.findViewById(R.id.total);
        }
        public void incrementa() {
            qtd++;
            textqtd.setText(String.valueOf(qtd) + " Itens");
        }
    }


}
