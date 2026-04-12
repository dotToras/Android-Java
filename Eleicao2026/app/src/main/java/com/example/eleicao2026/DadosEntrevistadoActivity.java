package com.example.eleicao2026;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eleicao2026.database.AppDatabase;
import com.example.eleicao2026.models.Entrevistado;
import com.example.eleicao2026.utils.SessaoPesquisa;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class DadosEntrevistadoActivity extends AppCompatActivity {

    // essa função verifica as respostas das permissões, se foram concedidas ou negadas
    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);

                if (fineLocationGranted != null && fineLocationGranted) {
                    // permissão total mais aproximada
                    pegarLocalizacaoAtual();
                } else if (coarseLocationGranted != null && coarseLocationGranted) {
                    // localização aproximada
                    pegarLocalizacaoAtual();
                } else {
                    // negou a permissão
                    Toast.makeText(this, "Precisamos da permissão para salvar seus Dados", Toast.LENGTH_SHORT).show();
                }
            });

    // Classe da API do Google Services para pegar a localização atual
    private FusedLocationProviderClient fusedLocationClient;

    Button btConfDados, btFinalizarPes;
    TextView etCelEntr, etNomeEntr;
    double latitude, longitude;


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
        // instancia do flpc
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        pegarLocalizacaoAtual();

        btConfDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // desativa o botão enquanto ocorre o salvamento
                btConfDados.setEnabled(false);

                // Salvando Dados do Entrevistado
                new Thread(() -> {

                    Entrevistado ev = new Entrevistado();
                    ev.setNome(etNomeEntr.getText().toString());
                    ev.setCelular(etCelEntr.getText().toString());
                    ev.setDataHora(System.currentTimeMillis());
                    ev.setLatitude(latitude);
                    ev.setLongitude(longitude);
                    db.entrevistadoDAO().criar(ev);



                    // para continuar somente apos terminar a thread
                    runOnUiThread(()-> {

                        // limpa a sessao
                        SessaoPesquisa.limpar();

                        // mandar para otura tela
                        Intent i = new Intent(DadosEntrevistadoActivity.this, MainActivity.class);
                        startActivity(i);
                        finishAndRemoveTask();
                    });
                }).start();
            }
        });
    }

    void pegarLocalizacaoAtual() {


        // verifica se há as permissões
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            verificarPermissoes();
            return;
        }
        // pegando a localização
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location localizacao) {
                if(localizacao != null) {
                    // bota a latitude e longitude em variaveis
                    latitude = localizacao.getLatitude();
                    longitude = localizacao.getLongitude();
                }
                else {
                   Toast t = Toast.makeText(DadosEntrevistadoActivity.this, "Ligue o GPS para salvar seus dados", Toast.LENGTH_SHORT);
                   t.show();
                }
            }
        });
    }

    // dispara a janelinha pedindo permissão
    private void verificarPermissoes() {

        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
}

// documentação usada para a localizacao:
// Aidicionar no Manifest: https://developer.android.com/develop/sensors-and-location/location/permissions?hl=pt-br
// Fused Location Provider e pegar a última localização: https://developer.android.com/develop/sensors-and-location/location/retrieve-current?hl=pt-br
// Dependencia: https://developers.google.com/android/guides/setup?hl=pt-br&device=phone-tablet
// Criar o pedido de permissão (runtime): https://developer.android.com/develop/sensors-and-location/location/permissions/runtime?hl=pt-br#java