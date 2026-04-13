package com.example.eleicao2026;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eleicao2026.database.AppDatabase;
import com.example.eleicao2026.models.CandidatoVoto;
import com.example.eleicao2026.models.Voto;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraficoActivity extends AppCompatActivity {

    HorizontalBarChart bcIntencaoEstimulada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grafico);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bcIntencaoEstimulada = (HorizontalBarChart) findViewById(R.id.bcIntencaoEstimulada);
        AppDatabase db = AppDatabase.getINSTANCE(this);

        List<BarEntry> listaEntradas = new ArrayList<>();


        // buscando dados para o grafico
        new Thread(()->{

            List<CandidatoVoto> listaVotosPorCandidato = db.candidatoVotoDAO().buscarVotosCandidatos();
            List<String> listaNomesCandidatos = new ArrayList<>();
            List<String> listaCoresPartidos = new ArrayList<>();

            int totalVotos = db.votoDAO().buscarTotalVotos();


            listaEntradas.clear();
            listaNomesCandidatos.clear();
            listaCoresPartidos.clear();

            for(int i = 0; i < listaVotosPorCandidato.size(); i++) {
                CandidatoVoto cv = listaVotosPorCandidato.get(i);
                listaEntradas.add(new BarEntry(i, (((float) cv.getQtdVotos() / totalVotos) * 100)));
                listaNomesCandidatos.add(cv.getCan_nome());
                listaCoresPartidos.add(cv.getCan_partidoCor());
            }

            List<Integer> cores = new ArrayList<>();

            // transformando as cores hexadecimal em integer
            for(String cor : listaCoresPartidos) {
                cores.add(Color.parseColor(cor));
            }


            runOnUiThread(()-> {
                BarDataSet bdt = new BarDataSet(listaEntradas, "Intenção de Votos");
                bdt.setColors(cores);
                bdt.setValueTextSize(12f);
                bdt.setDrawValues(true);

                BarData barData = new BarData(bdt);
                barData.setBarWidth(0.9f);


                // Configura os nomes no eixo X
                XAxis xAxis = bcIntencaoEstimulada.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(listaNomesCandidatos));
                xAxis.setGranularity(1f); // evita repetir labels se houver poucos dados
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                xAxis.setCenterAxisLabels(false);
                xAxis.setLabelCount(listaNomesCandidatos.size());

                YAxis yAxis = bcIntencaoEstimulada.getAxisLeft();
                yAxis.setAxisMinimum(0f);
                yAxis.setAxisMaximum(100f);

                bcIntencaoEstimulada.getAxisRight().setEnabled(false);
                bcIntencaoEstimulada.setData(barData);
                bcIntencaoEstimulada.setFitBars(true);
                bcIntencaoEstimulada.invalidate(); // Recarrega o gráfico

            });
        }).start();

    }
}