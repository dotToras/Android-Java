package com.example.eleicao2026;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eleicao2026.database.AppDatabase;
import com.example.eleicao2026.models.Usuario;


public class LoginActivity extends AppCompatActivity {

    EditText edUsuario, edSenha;
    Button btEntrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        btEntrar = findViewById(R.id.btEntrar);
        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);


        AppDatabase db = AppDatabase.getINSTANCE(this);



        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                new Thread(() -> {

                    if(db.usuarioDAO().buscarTotalUsuarios() == 0) {
                        Usuario admin = new Usuario();
                        admin.setNome("admin");
                        admin.setSenha("123");
                        admin.setTipo("admin");
                        db.usuarioDAO().criar(admin);

                        Usuario entrevistador = new Usuario();
                        entrevistador.setNome("Mario");
                        entrevistador.setSenha("123");
                        entrevistador.setTipo("entrevistador");
                        db.usuarioDAO().criar(entrevistador);
                    }
                    Usuario usuarioLogado = db.usuarioDAO().fazerLogin(edUsuario.getText().toString(), edSenha.getText().toString());

                    // usado quando queremos mostrar imagens na tela
                    runOnUiThread(() -> {
                        Intent i = null;
                        if(usuarioLogado != null){

                            Toast toast = Toast.makeText(LoginActivity.this, "Bem-vindo!", Toast.LENGTH_SHORT);
                            toast.show();

                            if(usuarioLogado.getTipo().equals("admin")) {
                                i = new Intent(LoginActivity.this, ResultadoPesquisaActivity.class);
                            }
                            else {
                                i = new Intent(LoginActivity.this, MainActivity.class);
                            }

                            startActivity(i);
                            finish();
                        }
                        else{
                            //Mensagem de aviso
                            String text = "Email ou senha incorretos!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(LoginActivity.this, text, duration);
                            toast.show();
                        }
                    });
                }).start();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}