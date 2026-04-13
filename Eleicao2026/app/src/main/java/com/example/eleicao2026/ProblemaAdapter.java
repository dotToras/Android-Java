package com.example.eleicao2026;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleicao2026.models.Problema;
import com.example.eleicao2026.utils.SessaoPesquisa;

import java.util.ArrayList;
import java.util.List;

public class ProblemaAdapter extends RecyclerView.Adapter<ProblemaAdapter.ProblemaViewHolder> {

    private List<Problema> listaProblemas;
    // Lista para controlar quais nomes foram selecionados
    private List<String> selecionados = new ArrayList<>();

    public ProblemaAdapter(List<Problema> lista) {
        this.listaProblemas = lista;
    }

    @NonNull
    @Override
    public ProblemaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_problema, parent, false);
        return new ProblemaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemaViewHolder holder, int position) {
        Problema problema = listaProblemas.get(position);
        holder.tvNomeProblema.setText(problema.getNome());

        // limpa o listener antes de setar o estado
        holder.checkProblema.setOnCheckedChangeListener(null);

        // define o estado visual baseado na lista de selecionados
        boolean estaSelecionado = selecionados.contains(problema.getNome()); // variavel para verificar se o problema ja esta na lista

        holder.checkProblema.setChecked(estaSelecionado);
        holder.cardProblema.setCardBackgroundColor(estaSelecionado ? Color.parseColor("#F0F0F0") : Color.WHITE); // se esta selecionado destaca, senão, bota a cor origina

        // Verifica o click
        holder.checkProblema.setOnCheckedChangeListener((buttonView, isChecked) -> {

            // se esta checkado
            if (isChecked) {
                // verifica se já atingiu o limite de 3
                if (selecionados.size() < 3) {
                    selecionados.add(problema.getNome());
                    holder.cardProblema.setCardBackgroundColor(Color.parseColor("#F0F0F0"));
                } else {
                    // cancela a marcação se já tiver 3
                    buttonView.setChecked(false);
                    Toast.makeText(buttonView.getContext(), "Selecione no máximo 3!", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                selecionados.remove(problema.getNome());
                holder.cardProblema.setCardBackgroundColor(Color.WHITE);
            }

            SessaoPesquisa.problemasMarcados = new ArrayList<>(selecionados);
            notifyItemChanged(holder.getBindingAdapterPosition());
        });

        // clicar no card aciona o CheckBox
        holder.itemView.setOnClickListener(v -> holder.checkProblema.toggle());
    }

    @Override
    public int getItemCount() {
        return listaProblemas.size();
    }

    public static class ProblemaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeProblema;
        CheckBox checkProblema;
        CardView cardProblema;

        public ProblemaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNomeProblema = itemView.findViewById(R.id.tvNomeProblema);
            checkProblema = itemView.findViewById(R.id.checkProblema);
            cardProblema = itemView.findViewById(R.id.cardProblema);
        }
    }
}