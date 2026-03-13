package com.example.quizads;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText edUsuario, edSenha;
    Button btAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btAcessar = findViewById(R.id.btAcessar);
        edUsuario = findViewById(R.id.edUsuario);
        edSenha = findViewById(R.id.edSenha);

        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "Mario";
                String senha = "1234";

                if(edUsuario.getText().toString().equals(email) && edSenha.getText().toString().equals(senha)){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                    //Mensagem de aviso
                    String text = "Bem-vindo!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(LoginActivity.this, text, duration);
                    toast.show();
                }
                else{
                    //Mensagem de aviso
                    String text = "Email ou senha incorretos!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(LoginActivity.this, text, duration);
                    toast.show();
                }
            }
        });
    }
}