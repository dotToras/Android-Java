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
import com.example.eleicao2026.models.Entrevistado;
import com.example.eleicao2026.utils.SessaoPesquisa;

public class DadosEntrevistadoActivity extends AppCompatActivity {

    Button btConfDados, btFinalizarPes;
    TextView etCelEntr, etNomeEntr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dados_entrevistado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btConfDados = findViewById(R.id.btConfDados);
        btFinalizarPes = findViewById(R.id.btFinalizarPes);
        etCelEntr = findViewById(R.id.etCelEntr);
        etNomeEntr = findViewById(R.id.etNomeEntr);
        AppDatabase db = AppDatabase.getINSTANCE(this);

        btConfDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Salvando Dados do Entrevistado
                new Thread(() -> {

                    Entrevistado ev = new Entrevistado();
                    ev.setNome(etNomeEntr.getText().toString());
                    ev.setCelular(etCelEntr.getText().toString());
                    ev.setDataHora(System.currentTimeMillis());
                    db.entrevistadoDAO().criar(ev);

                }).start();



                // limpa a sessao
                SessaoPesquisa.limpar();

                // manter
                Intent i = new Intent(DadosEntrevistadoActivity.this, MainActivity.class);
                startActivity(i);
                finishAndRemoveTask();
            }
        });
    }
}