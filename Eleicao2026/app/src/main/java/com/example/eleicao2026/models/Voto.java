package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = Candidato.class, // onde está a PK
        parentColumns = "can_codigo", // nome da coluna na abela Candidato
        childColumns = "can_codigo" // nome da coluna na tabela onde esta a FK
))
public class Voto {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "vot_codigo")
    private int codigo;
    @ColumnInfo(name = "can_codigo")
    private Integer candidato_codigo; // aqui eh um Integer porque pode ser nulo, int nao aceita

    @ColumnInfo(name = "vot_nomeCitado")
    private String nomeCitado;

    @ColumnInfo(name = "vot_tipoPesquisa")
    private String tipoPesquisa;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Integer getCandidato_codigo() {
        return candidato_codigo;
    }

    public void setCandidato_codigo(Integer candidato_codigo) {
        this.candidato_codigo = candidato_codigo;
    }

    public String getNomeCitado() {
        return nomeCitado;
    }

    public void setNomeCitado(String nomeCitado) {
        this.nomeCitado = nomeCitado;
    }

    public String getTipoPesquisa() {
        return tipoPesquisa;
    }

    public void setTipoPesquisa(String tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }


}
