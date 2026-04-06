package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Problema {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pro_codigo")
    private int codigo;

    @ColumnInfo(name = "pro_nome")
    private String nome;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
