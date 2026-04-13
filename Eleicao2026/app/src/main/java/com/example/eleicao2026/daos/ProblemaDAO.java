package com.example.eleicao2026.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.eleicao2026.models.Problema;

import java.util.List;
@Dao
public interface ProblemaDAO {

    @Query("Select * From Problema")
    List<Problema> buscarTodos();

    @Query("SELECT pro_codigo FROM Problema WHERE pro_nome = :nome")
    int buscarIdPeloNome(String nome);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void criar(Problema problema);

    @Update
    void atualizar(Problema problema);

}
