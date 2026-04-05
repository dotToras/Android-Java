package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "usu_codigo")
    public int codigo;

    @ColumnInfo(name = "usu_nome")
    public String nome;

    @ColumnInfo(name = "usu_senha")
    public String senha;

    @ColumnInfo(name = "usu_tipo")
    public String tipo;

}
