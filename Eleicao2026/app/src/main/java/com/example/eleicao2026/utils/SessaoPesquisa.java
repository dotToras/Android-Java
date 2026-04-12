package com.example.eleicao2026.utils;

import com.example.eleicao2026.models.Problema;
import com.example.eleicao2026.models.Voto;

import java.util.ArrayList;
import java.util.List;

public class SessaoPesquisa {
    public static Voto votoEspontaneo = new Voto();
    public static Voto votoEstimulado = new Voto();
    public static List<Problema> problemasMarcados = new ArrayList<>();
    
    public static void limpar() {
        votoEspontaneo = new Voto();
        votoEstimulado = new Voto();
        problemasMarcados = new ArrayList<>();
    }
}