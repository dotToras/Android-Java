package com.example.quizads;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlActivity extends AppCompatActivity {



    LinearLayout containerQuiz;
    Button btEnviar;
    List<Questao> listaQuestoes = new ArrayList<>();
    int numb;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sql);


        if( Global.prova == 1) {
            preencherQuestoesSQL();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btEnviar = findViewById(R.id.btEnviar);
        containerQuiz = findViewById(R.id.layout01);




        TextView tv = new TextView(this);

        for(int i = 0; i < listaQuestoes.size(); i++) {

            Questao q = listaQuestoes.get(i);

            TextView tvPergunta = new TextView(this);
            tvPergunta.setText(i + 1 + "-" + q.getQuestao());
            tvPergunta.setTextSize(18);
            RadioGroup rg = new RadioGroup(this);
            rg.addView(tvPergunta);
            rg.setOrientation(RadioGroup.VERTICAL);

            for (int j = 0; j < 4; j++) {
                RadioButton rbOpcoes = new RadioButton(this);
                rbOpcoes.setText(q.getPerguntas().get(j));
                rbOpcoes.setId(j);

                rg.addView(rbOpcoes);
            }
            containerQuiz.addView(rg);

        }


}


    public void preencherQuestoesSQL() {

        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
    }

    public void preencherQuestoesJava() {

        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
    }

    public void preencherQuestoesSO() {

        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
        listaQuestoes.add(new Questao("Qual é o SGBD Mais  popular do mundo?", Arrays.asList("Oracle", "PostgreSQL", "MYSQL", "SQL SERVER"), "Oracle" ));
        listaQuestoes.add(new Questao("teste?", Arrays.asList("te", "testse", "fd", "fds"), "Oracle" ));
    }
}

