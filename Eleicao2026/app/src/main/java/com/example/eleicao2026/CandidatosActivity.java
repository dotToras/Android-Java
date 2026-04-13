package com.example.eleicao2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleicao2026.database.AppDatabase;
import com.example.eleicao2026.models.Candidato;
import com.example.eleicao2026.utils.SessaoPesquisa;

import java.util.List;

public class CandidatosActivity extends AppCompatActivity {

    Button btConfiEst;
    RecyclerView rvListaCandidatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_candidatos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btConfiEst = findViewById(R.id.btConfiEst);
        rvListaCandidatos = findViewById(R.id.rvListaCandidatos);
        rvListaCandidatos.setLayoutManager(new LinearLayoutManager(this));
        AppDatabase db = AppDatabase.getINSTANCE(this);


        new Thread(()->{

            // inserindo os candidatos
            Candidato c = new Candidato();

            c.setNome("Ea-Nasir");
            c.setPartido("SUMR");
            c.setFotoUrl("gq79pkhxkaa4a5e");
            c.setTipo("Candidato");

            db.candidatoDAO().criar(c);

            // buscando os candidatos
            List<Candidato> listaCandidatosDb = db.candidatoDAO().buscarTodos();

            runOnUiThread(()->{
                CandidatosAdapter adapter = new CandidatosAdapter(listaCandidatosDb);
                rvListaCandidatos.setAdapter(adapter);
            });
        }).start();

        btConfiEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // manda para a tela
                Intent i = new Intent(CandidatosActivity.this, ProblemasActivity.class);
                startActivity(i);
                finishAndRemoveTask();
            }
        });
    }
}