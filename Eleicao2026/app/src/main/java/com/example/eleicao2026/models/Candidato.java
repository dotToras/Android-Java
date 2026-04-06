package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Candidato {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "can_codigo")
    private int codigo;
    @ColumnInfo(name = "can_nome")
    private String nome;
    @ColumnInfo(name = "can_fotoUrl")
    private String fotoUrl;

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

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}
