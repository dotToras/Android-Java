package com.example.quizads;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Questao {

    String questao;
    List<String> perguntas = new ArrayList<>();
    String respostaCerta;

    public Questao(String questao, String respostaCerta, String ... perguntas) {
        this.questao = questao;
        this.respostaCerta = respostaCerta;
        // assim que chamar o construtor já popular as perguntas
        this.perguntas.add(perguntas[0]);
        this.perguntas.add(perguntas[1]);
        this.perguntas.add(perguntas[2]);
        this.perguntas.add(perguntas[3]);
    }


}
