package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Problema {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pro_codigo")
    public int codigo;

    @ColumnInfo(name = "pro_nome")
    public String nome;

}
