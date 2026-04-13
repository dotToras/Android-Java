package com.example.eleicao2026;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleicao2026.models.Candidato;
import com.example.eleicao2026.utils.SessaoPesquisa;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CandidatosAdapter extends RecyclerView.Adapter<CandidatosAdapter.CandidatosViewHolder> {

    private List<Candidato> listaCandidatos;
    private int itemSelecionado = -1; // inicia sem nenhum item selecionado

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

        int cor = Color.parseColor(candidato.getCorPartido());
        holder.tvPartido.setTextColor(cor);
        holder.vwBordaCor.setBackgroundColor(cor);


        // mostr visualmente qual o candidato selecionado
        if (itemSelecionado == position) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#C1C1C1"));
        } else {
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }

        // definindo onOnlick para salvar objeto do voto
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelecionado = holder.getBindingAdapterPosition();
                notifyDataSetChanged(); // Atualiza a lista para pintar o selecionado

                //salva o objeto
                SessaoPesquisa.votoEstimulado.setCandidato_codigo(candidato.getCodigo());
                SessaoPesquisa.votoEstimulado.setTipoPesquisa("ESTIMULADA");

                // Notifica o usuario em quem ele esta selecionado
                Toast.makeText(v.getContext(), "Você selecionou: " + candidato.getNome(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCandidatos.size();
    }

    // O ViewHolder (o "guardador")
    public static class CandidatosViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartido, tvNomeCand;
        View vwBordaCor;
        ImageView imgCand;
        CardView cardView;

        public CandidatosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPartido = itemView.findViewById(R.id.tvPartido);
            tvNomeCand = itemView.findViewById(R.id.tvNomeCand);
            imgCand = itemView.findViewById(R.id.imgCand);
            vwBordaCor = itemView.findViewById(R.id.vwBordaCor);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

}
