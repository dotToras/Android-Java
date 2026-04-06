package com.example.eleicao2026.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        primaryKeys = {"pro_codigo", "vot_codigo"},
        foreignKeys = {
                @ForeignKey(
                    entity = Problema.class,
                    parentColumns = "pro_codigo",
                    childColumns = "pro_codigo",
                    onDelete = ForeignKey.CASCADE // se o Problema for apagado, limpa o vinculo
                ),
                @ForeignKey(
                        entity = Voto.class,
                        parentColumns = "vot_codigo",
                        childColumns = "vot_codigo",
                        onDelete = ForeignKey.CASCADE // se o Voto for apagado, limpa o vinculo
                )
        }
)
public class ProblemaVoto {

    @ColumnInfo(name = "pro_codigo")
    private int problemaCodigo;

    @ColumnInfo(name = "vot_codigo")
    private int votoCodigo;

    public ProblemaVoto(int problemaCodigo, int votoCodigo) {
        this.problemaCodigo = problemaCodigo;
        this.votoCodigo = votoCodigo;
    }

    public int getProblemaCodigo() {
        return problemaCodigo;
    }

    public void setProblemaCodigo(int problemaCodigo) {
        this.problemaCodigo = problemaCodigo;
    }

    public int getVotoCodigo() {
        return votoCodigo;
    }

    public void setVotoCodigo(int votoCodigo) {
        this.votoCodigo = votoCodigo;
    }
}
