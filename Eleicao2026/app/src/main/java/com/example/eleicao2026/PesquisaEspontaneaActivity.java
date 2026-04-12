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

import com.example.eleicao2026.utils.SessaoPesquisa;

public class PesquisaEspontaneaActivity extends AppCompatActivity {

    Button btConfEsp;

    TextView etNomeCandEsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pesquisa_espontanea);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btConfEsp = findViewById(R.id.btConfEsp);
        etNomeCandEsp = findViewById(R.id.etNomeCandEsp);

        btConfEsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // salva na lista
                SessaoPesquisa.votoEspontaneo.setNomeCitado(etNomeCandEsp.getText().toString());
                SessaoPesquisa.votoEspontaneo.setTipoPesquisa("ESPONTANEA");

                // manda para a proxima tela
                Intent i = new Intent(PesquisaEspontaneaActivity.this, CandidatosActivity.class);
                startActivity(i);
                finishAndRemoveTask();
            }
        });

    }
}