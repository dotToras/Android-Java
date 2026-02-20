package com.example.mobile_aula_inaugural;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MediaActivity extends AppCompatActivity {

    //Declaração de Variáveis
    Button btCalcular, btLimpar;
    EditText edDisciplina, edNota1, edNota2, edFaltas;
    TextView tvMedia, tvSituacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_media);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Associação das variáveis aos componentes da Activity
        btCalcular = findViewById(R.id.btCalcular);
        btLimpar = findViewById(R.id.btLimpar);

        edDisciplina = findViewById(R.id.edDisciplina);
        edNota1 = findViewById(R.id.edNota1);
        edNota2 = findViewById(R.id.edNota2);
        edFaltas = findViewById(R.id.edFaltas);

        tvMedia = findViewById(R.id.tvMedia);
        tvSituacao = findViewById(R.id.tvSituacao);

        // Cria o metodo onClick do BotaoCalcular
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double nota1, nota2, media;
                int faltas;
                String situacao;

                nota1 = Double.parseDouble(edNota1.getText().toString());
                nota2 = Double.parseDouble(edNota2.getText().toString());
                faltas = Integer.parseInt(edFaltas.getText().toString());
                media = (nota1 + nota2) / 2;
                tvMedia.setText(String.valueOf(media));

                if(media >= 6 && faltas <= 5) {
                    tvSituacao.setText("Aprovado");
                    tvSituacao.setTextColor(Color.parseColor("#1fb51f"));
                }
                else {
                    tvSituacao.setText("Reprovado");
                    tvSituacao.setTextColor(Color.parseColor("#b51f1f"));
                }

            }
        });

        // Metodo para onlick do botão limpar
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edDisciplina.setText("");
                edNota1.setText("");
                edNota2.setText("");
                edFaltas.setText("");
                tvMedia.setText("0.0");
                tvSituacao.setText("Pendente");
                tvSituacao.setTextColor(Color.LTGRAY);
            }
        });
    }
}