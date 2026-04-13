package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"can_nome"}, unique = true)})
public class Candidato {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "can_codigo")
    private int codigo;

    @ColumnInfo(name = "can_nome")
    private String nome;

    @ColumnInfo(name = "can_partido")
    private String partido;
    @ColumnInfo(name = "can_partidoCor")
    private String corPartido;

    @ColumnInfo(name = "can_fotoUrl")
    private String fotoUrl;

    // "CANDIDATO", "ESPECIAL"
    @ColumnInfo(name = "can_tipo")
    private String tipo;

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

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorPartido() {
        return corPartido;
    }

    public void setCorPartido(String corPartido) {
        this.corPartido = corPartido;
    }
}