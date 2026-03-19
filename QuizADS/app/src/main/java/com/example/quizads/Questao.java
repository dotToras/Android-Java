package com.example.quizads;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Questao {

    private String questao;
    private List<String> perguntas;
    private String respostaCerta;

    public Questao(String questao, List<String> perguntas, String respostaCerta) {
        this.questao = questao;
        this.perguntas = perguntas;
        this.respostaCerta = respostaCerta;
    }



    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<String> perguntas) {
        this.perguntas = perguntas;
    }

    public String getRespostaCerta() {
        return respostaCerta;
    }

    public void setRespostaCerta(String respostaCerta) {
        this.respostaCerta = respostaCerta;
    }
}
