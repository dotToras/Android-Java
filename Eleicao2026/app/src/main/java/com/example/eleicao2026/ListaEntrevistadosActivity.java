package com.example.eleicao2026;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eleicao2026.database.AppDatabase;
import com.example.eleicao2026.models.Entrevistado;

import java.util.List;

public class ListaEntrevistadosActivity extends AppCompatActivity {

    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_entrevistados);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rv = findViewById(R.id.rvEntrevistados);
        AppDatabase db = AppDatabase.getINSTANCE(this);
        // Define como a lista aparece, no caso escolhi um embaixo do outro
        rv.setLayoutManager(new LinearLayoutManager(this));

        new Thread(()->{

           List<Entrevistado> listaEntrevistadosDb = db.entrevistadoDAO().buscarTodos();

           // atualizando a tela
            runOnUiThread(()->{
                EntrevistadoAdapter adapter = new EntrevistadoAdapter(listaEntrevistadosDb);
                rv.setAdapter(adapter);
            });


        }).start();

    }
}