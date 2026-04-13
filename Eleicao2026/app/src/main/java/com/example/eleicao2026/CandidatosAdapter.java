package com.example.eleicao2026;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleicao2026.models.Candidato;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CandidatosAdapter extends RecyclerView.Adapter<CandidatosAdapter.CandidatosViewHolder> {

    private List<Candidato> listaCandidatos;

    public CandidatosAdapter(List<Candidato> lista) {
        this.listaCandidatos = lista;
    }

    @NonNull
    @Override
    public CandidatosAdapter.CandidatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Aqui  "infla" o layout do item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candidato, parent, false);
        return new CandidatosAdapter.CandidatosViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CandidatosAdapter.CandidatosViewHolder holder, int position) {
        // Aqui  preenche os dados
        Candidato candidato = listaCandidatos.get(position);
        holder.tvNomeCand.setText(candidato.getNome());
        holder.tvPartido.setText(candidato.getPartido());

        // Converte o nome (String) em ID (int)
        Context context = holder.itemView.getContext();
        int resId = context.getResources().getIdentifier(
                candidato.getFotoUrl(),
                "drawable",
                context.getPackageName()
        );

        if (resId != 0) {
            holder.imgCand.setImageResource(resId);
        } else {
            // Se a foto não for encontrada ou o campo estiver vazio
            holder.imgCand.setImageResource(R.drawable.icons8_nome_96);
        }

    }

    @Override
    public int getItemCount() {
        return listaCandidatos.size();
    }

    // O ViewHolder (o "guardador")
    public static class CandidatosViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartido, tvNomeCand;
        ImageView imgCand;

        public CandidatosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPartido = itemView.findViewById(R.id.tvPartido);
            tvNomeCand = itemView.findViewById(R.id.tvNomeCand);
            imgCand = itemView.findViewById(R.id.imgCand);

        }
    }

}
