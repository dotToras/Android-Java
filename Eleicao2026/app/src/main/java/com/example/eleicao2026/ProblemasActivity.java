package com.example.eleicao2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleicao2026.database.AppDatabase;
import com.example.eleicao2026.models.Problema;
import com.example.eleicao2026.utils.SessaoPesquisa;

import java.util.List;

public class ProblemasActivity extends AppCompatActivity {

    Button btConfProb;
    RecyclerView rvListaProb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_problemas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btConfProb = findViewById(R.id.btConfProb);
        rvListaProb = findViewById(R.id.rvListaProb);
        rvListaProb.setLayoutManager(new LinearLayoutManager(this));
        AppDatabase db = AppDatabase.getINSTANCE(this);



        new Thread(()->{

            // salvando os problemas
            salvarProblema(db,"Saúde");
            salvarProblema(db,"Educação");
            salvarProblema(db,"Transporte");
            salvarProblema(db,"Violência");
            salvarProblema(db,"Fome");
            salvarProblema(db,"Pobreza");
            salvarProblema(db,"Trabalho");
            salvarProblema(db,"Saneamento Básico");
            salvarProblema(db,"Moradia");
            salvarProblema(db,"Corrupção");


            // buscando para popular a lista
            List<Problema> listaProblemasDb = db.problemaDAO().buscarTodos();

            runOnUiThread(()->{

                ProblemaAdapter adapter = new ProblemaAdapter(listaProblemasDb);
                rvListaProb.setAdapter(adapter);

            });

        }).start();

        btConfProb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SessaoPesquisa.problemasMarcados == null || SessaoPesquisa.problemasMarcados.isEmpty()) {
                    Toast t = Toast.makeText(ProblemasActivity.this, "Escolha ao menos 1 problema", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    Intent i = new Intent(ProblemasActivity.this, DadosEntrevistadoActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }

    private void salvarProblema(AppDatabase db, String nome) {
        Problema p = new Problema();
        p.setNome(nome);
        db.problemaDAO().criar(p);
    }
}