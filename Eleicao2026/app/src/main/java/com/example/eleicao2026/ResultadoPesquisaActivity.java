package com.example.eleicao2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eleicao2026.database.AppDatabase;

public class ResultadoPesquisaActivity extends AppCompatActivity {

    TextView tvQtdEntr;
    Button btResultadoPes, btEleitoresPes, btFinalizarRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado_pesquisa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvQtdEntr = findViewById(R.id.tvQtdEntr);
        AppDatabase db = AppDatabase.getINSTANCE(this);
        btResultadoPes = findViewById(R.id.btResultadoPes);
        btEleitoresPes = findViewById(R.id.btEleitoresPes);
        btFinalizarRes = findViewById(R.id.btFinalizarRes);

        // selecionando total de entrevistados
        new Thread(() ->{
            int qtdEntrevistados = db.entrevistadoDAO().totalEntrevistados();
            runOnUiThread(()->{
                tvQtdEntr.setText(String.valueOf(qtdEntrevistados));
            });
        }).start();

        btResultadoPes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultadoPesquisaActivity.this, GraficoActivity.class);
                startActivity(i);

            }
        });

        btEleitoresPes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultadoPesquisaActivity.this, ListaEntrevistadosActivity.class);
                startActivity(i);

            }
        });

        btFinalizarRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultadoPesquisaActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}