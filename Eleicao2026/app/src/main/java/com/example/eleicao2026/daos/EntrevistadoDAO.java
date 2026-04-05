package com.example.eleicao2026.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eleicao2026.models.Entrevistado;

import java.util.List;

@Dao
public interface EntrevistadoDAO {

    @Query("Select * From Entrevistado")
    List<Entrevistado> buscarTodos();

    @Insert
    void criar(Entrevistado entrevistado);

    @Query("Select COUNT(*) from Entrevistado")
    int totalEntrevistados();

    @Delete
    void deletar(Entrevistado entrevistado);

}
