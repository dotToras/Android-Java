package com.example.eleicao2026.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.eleicao2026.models.ProblemaVoto;
import com.example.eleicao2026.models.Voto;

import java.util.List;

@Dao
public interface VotoDAO {

    @Insert
    long inserirVoto(Voto voto); // long para retornar o id do Voto

    @Insert
    void inserirRelacao(ProblemaVoto pv);

    @Transaction
    default void salvarVoto(Voto v, List<Integer> codProblemas) {

        long votoId = inserirVoto(v);

        // para cada codigo de problema na lista
        for(Integer codPro : codProblemas) {
            inserirRelacao(new ProblemaVoto((int) votoId, (int) votoId)); // insere a relação com o voto
        }
    }
    @Query("Select Count(*) From Voto where can_codigo = :candidatoId")
    int buscarTotalVotosCandidato(int candidatoId);

}
