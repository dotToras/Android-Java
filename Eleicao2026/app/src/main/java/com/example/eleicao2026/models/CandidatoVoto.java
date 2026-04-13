package com.example.eleicao2026.models;

public class CandidatoVoto {

    private String can_nome;

    private int qtdVotos;

    private String can_partidoCor;

    public String getCan_nome() {
        return can_nome;
    }

    public void setCan_nome(String can_nome) {
        this.can_nome = can_nome;
    }

    public int getQtdVotos() {
        return qtdVotos;
    }

    public void setQtdVotos(int qtdVotos) {
        this.qtdVotos = qtdVotos;
    }

    public String getCan_partidoCor() {
        return can_partidoCor;
    }

    public void setCan_partidoCor(String can_partidoCor) {
        this.can_partidoCor = can_partidoCor;
    }
}
