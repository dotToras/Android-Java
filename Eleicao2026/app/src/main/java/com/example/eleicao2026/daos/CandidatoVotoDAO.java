package com.example.eleicao2026.daos;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.eleicao2026.models.CandidatoVoto;

import java.util.List;

@Dao
public interface CandidatoVotoDAO {

    @Query("SELECT can_nome, can_partidoCor, COUNT(*) as qtdVotos FROM Candidato " +
            "INNER JOIN Voto USING (can_codigo)" +
            "GROUP BY can_nome")
    List<CandidatoVoto> buscarVotosCandidatos();

}
