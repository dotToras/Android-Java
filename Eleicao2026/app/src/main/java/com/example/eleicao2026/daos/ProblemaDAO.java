package com.example.eleicao2026.daos;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.eleicao2026.models.Problema;

import java.util.List;

public interface ProblemaDAO {

    @Query("Select * From Problema")
    List<Problema> buscarTodos();

    @Insert
    void criar(Problema problema);

    @Update
    void atualizar(Problema problema);

}
