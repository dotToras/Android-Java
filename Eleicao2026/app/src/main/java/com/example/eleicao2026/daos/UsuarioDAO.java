package com.example.eleicao2026.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.eleicao2026.models.Usuario;

@Dao
public interface UsuarioDAO {


    @Query("Select * from Usuario Where usu_nome = :nome And usu_senha = :senha")
    Usuario fazerLogin(String nome, String senha);

    @Insert
    void criar(Usuario usuario);

    @Query("Select COUNT(*) from Usuario")
    int buscarTotalUsuarios();

}
