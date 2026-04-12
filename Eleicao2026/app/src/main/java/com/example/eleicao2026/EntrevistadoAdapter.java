package com.example.eleicao2026;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleicao2026.models.Entrevistado;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EntrevistadoAdapter extends RecyclerView.Adapter<EntrevistadoAdapter.EntrevistadoViewHolder> {

    private List<Entrevistado> listaEntrevistados;

    public EntrevistadoAdapter(List<Entrevistado> lista) {
        this.listaEntrevistados = lista;
    }

    @NonNull
    @Override
    public EntrevistadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Aqui  "infla" o layout do item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entrevistado, parent, false);
        return new EntrevistadoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EntrevistadoViewHolder holder, int position) {
        // Aqui  preenche os dados
        Entrevistado entrevistado = listaEntrevistados.get(position);
        holder.tvNome.setText(entrevistado.getNome());
        holder.tvCelular.setText(entrevistado.getCelular());
        holder.tvLatitude.setText(String.format("Lat: %.6f", entrevistado.getLatitude()));
        holder.tvLongitude.setText(String.format("Long: %.6f", entrevistado.getLongitude()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String dataFormatada = sdf.format(new Date(entrevistado.getDataHora()));
        holder.tvDataHora.setText("Data: " + dataFormatada);
    }

    @Override
    public int getItemCount() {
        return listaEntrevistados.size();
    }

    // O ViewHolder (o "guardador")
    public static class EntrevistadoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvCelular, tvLatitude, tvLongitude, tvDataHora;

        public EntrevistadoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvCelular = itemView.findViewById(R.id.tvCelular);
            tvLatitude = itemView.findViewById(R.id.tvLatitude);
            tvLongitude = itemView.findViewById(R.id.tvLongitude);
            tvDataHora = itemView.findViewById(R.id.tvDataHora);
        }
    }
}
