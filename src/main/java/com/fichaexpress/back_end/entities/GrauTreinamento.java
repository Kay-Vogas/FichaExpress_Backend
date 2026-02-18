package com.fichaexpress.back_end.entities;

import lombok.Getter;

@Getter
public enum GrauTreinamento {
    NENHUM(0),
    TREINADO(3),
    VETERANO(6),
    ESPECIALISTA(9),
    EXPERT(12);

    private final int bonus;

    GrauTreinamento(int bonus){
        this.bonus = bonus;
    }
}
