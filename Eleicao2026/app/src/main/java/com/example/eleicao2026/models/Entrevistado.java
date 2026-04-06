package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Entrevistado {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ent_codigo")
    private int codigo;

    @ColumnInfo(name = "ent_nome")
    private String nome;

    @ColumnInfo(name = "ent_celular")
    private String celular;

    @ColumnInfo(name = "ent_dataHora")
    private Long dataHora;

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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Long getDataHora() {
        return dataHora;
    }

    public void setDataHora(Long dataHora) {
        this.dataHora = dataHora;
    }
}
