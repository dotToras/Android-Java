package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Candidato {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "can_codigo")
    public int codigo;
    @ColumnInfo(name = "can_nome")
    public String nome;
    @ColumnInfo(name = "can_fotoUrl")
    public String fotoUrl;

}
