package com.example.quizads;

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

public class ResultadoActivity extends AppCompatActivity {

    TextView tvResumo,tvPorcentagem, tvPontuacao;
    Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        tvResumo = findViewById(R.id.tvResumo);
        tvPorcentagem = findViewById(R.id.tvPorcentagem);
        tvPontuacao = findViewById(R.id.tvPontuacao);
        btVoltar = findViewById(R.id.btVoltar);

        int acertos = getIntent().getIntExtra("ACERTOS", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        double pontuacao = getIntent().getDoubleExtra("PONTUACAO", 0.0);

        double porcentagem = ((double)  acertos / total) * 100;

        tvResumo.setText(acertos + " / " + total);
        tvPontuacao.setText(String.format("%.2f", pontuacao));
        tvPorcentagem.setText(String.format("Desempenho: %.1f%%", porcentagem));

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultadoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}