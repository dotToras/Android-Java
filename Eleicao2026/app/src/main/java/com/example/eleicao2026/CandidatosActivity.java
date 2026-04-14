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

   	        //  Cadastrando Candidatos
            salvarCandidato(db, "Ea-Nasir", "SUMR", "gq79pkhxkaa4a5e", "Candidato", "#CD7F32");
            salvarCandidato(db, "Ciro II", "PERS", "cyrus", "Candidato", "#4682B4");
            salvarCandidato(db, "Alexandre", "MACD", "alexandre", "Candidato", "#FFD700");
            salvarCandidato(db, "Gengis Khan", "MONG", "khan", "Candidato", "#8B0000");
            salvarCandidato(db, "Cleopatra", "EGIT", "cleopatra", "Candidato", "#008080");

    	    //  CAdastrando Opções especiais
            salvarCandidato(db, "Branco", "", "branco", "Especial", "#F5F5F5");
            salvarCandidato(db, "Nulo", "", "nulo", "Especial", "#E53935");
            salvarCandidato(db, "Não Sei", "", "naosei", "Especial", "#FFB300");
         

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

                // Verifica se selecionou um candidato antes de continuar para a proxima tela
                if (SessaoPesquisa.votoEstimulado == null || SessaoPesquisa.votoEstimulado.getCandidato_codigo() == null) {
                    Toast t = Toast.makeText(CandidatosActivity.this, "Escolha uma opção de voto", Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    // manda para a tela
                    Intent i = new Intent(CandidatosActivity.this, ProblemasActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });


    }

	private void salvarCandidato(AppDatabase db, String nome, String partido, String foto, String tipo, String cor) {
    	Candidato c = new Candidato();
    	c.setNome(nome);
    	c.setPartido(partido);
    	c.setFotoUrl(foto);
    	c.setTipo(tipo);
        c.setCorPartido(cor);

    	db.candidatoDAO().criar(c);
    }
}