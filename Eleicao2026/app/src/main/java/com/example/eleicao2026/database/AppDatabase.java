package com.example.eleicao2026.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.eleicao2026.daos.CandidatoDAO;
import com.example.eleicao2026.daos.EntrevistadoDAO;
import com.example.eleicao2026.daos.ProblemaDAO;
import com.example.eleicao2026.daos.UsuarioDAO;
import com.example.eleicao2026.daos.VotoDAO;
import com.example.eleicao2026.models.Candidato;
import com.example.eleicao2026.models.Entrevistado;
import com.example.eleicao2026.models.Problema;
import com.example.eleicao2026.models.ProblemaVoto;
import com.example.eleicao2026.models.Usuario;
import com.example.eleicao2026.models.Voto;

@Database(entities = { Usuario.class, Candidato.class, Entrevistado.class, Voto.class, Problema.class, ProblemaVoto.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Instancia
    // stactic associa à classe, não a instancia
    // volatile informa que a variavel pode ser alterada inesperada por multiplas thread ou hardware
    private static volatile AppDatabase INSTANCE;

    //DAOs
    public abstract UsuarioDAO usuarioDAO();
    public abstract CandidatoDAO candidatoDAO();
    public abstract ProblemaDAO problemaDAO();
    public abstract VotoDAO votoDAO();
    public abstract EntrevistadoDAO entrevistadoDAO();

    // Singleton, é importante aqui para que só seja possível instanciar a classe uma única vez no APP
    // evitando uso excessivo de memória


    public static AppDatabase getINSTANCE(final Context context) {

        synchronized (AppDatabase.class) {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "bdEleicao"
                        )
                        .fallbackToDestructiveMigration(true)
                        .build();
            }
        }
        return INSTANCE;
    }
}
