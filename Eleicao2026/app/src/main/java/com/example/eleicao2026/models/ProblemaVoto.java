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
    public int problemaCodigo;

    @ColumnInfo(name = "vot_codigo")
    public int votoCodigo;

    public ProblemaVoto(int problemaCodigo, int votoCodigo) {
        this.problemaCodigo = problemaCodigo;
        this.votoCodigo = votoCodigo;
    }
}
