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
    public int codigo;
    @ColumnInfo(name = "can_codigo")
    public Integer candidato_id; // aqui eh um Integer porque pode ser nulo, int nao aceita

    @ColumnInfo(name = "vot_nomeCitado")
    public String nomeCitado;


    @ColumnInfo(name = "vot_tipoPesquisa")
    public String tipoPesquisa;

    @ColumnInfo(name = "vot_latitude")
    public Double latitude;

    @ColumnInfo(name = "vot_longitude")
    public Double longitude;

}
