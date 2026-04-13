package com.example.eleicao2026.daos;

import com.example.eleicao2026.models.Candidato;
import androidx.room.Dao;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CandidatoDAO {

    @Query("Select * from Candidato")
    List<Candidato> buscarTodos();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void criar(Candidato candidato);

    @Update
    void atualizar(Candidato candidato);

    @Delete
    void deletar(Candidato candidato);

}
