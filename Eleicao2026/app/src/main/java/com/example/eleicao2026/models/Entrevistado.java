package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Entrevistado {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ent_codigo")
    public int codigo;

    @ColumnInfo(name = "ent_nome")
    public String nome;

    @ColumnInfo(name = "ent_celular")
    public String celular;

    @ColumnInfo(name = "ent_dataHora")
    public Long dataHora;
}
