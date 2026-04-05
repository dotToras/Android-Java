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
    int inserirVoto(Voto voto); // int para retornar o id do Voto

    @Insert
    void inserirRelacao(ProblemaVoto pv);

    @Transaction
    default void salvarVoto(Voto v, List<Integer> codProblemas) {

        int votoId = inserirVoto(v);

        // para cada codigo de problema na lista
        for(Integer codPro : codProblemas) {
            inserirRelacao(new ProblemaVoto(votoId, (int) votoId)); // insere a relação com o voto
        }
    }
    @Query("Select Count(*) From Voto where can_codigo = :candidatoId")
    int buscarTotalVotosCandidato(int candidatoId);

}
