package com.example.eleicao2026;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eleicao2026.database.AppDatabase;

public class ResultadoPesquisaActivity extends AppCompatActivity {

    TextView tvQtdEntr;

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

        new Thread(() ->{
            int qtdEntrevistados = db.entrevistadoDAO().totalEntrevistados();
            runOnUiThread(()->{
                tvQtdEntr.setText(String.valueOf(qtdEntrevistados));
            });
        }).start();

    }
}