package com.fichaexpress.back_end.entities;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;


@Embeddable //Anotação indica que a classe não poussui identidade própria

@Getter @Setter
public class PericiasAbyssal {

    @Enumerated(EnumType.STRING)
    private GrauTreinamento acrobacia = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento adestramento = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento artes = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento atletismo = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento atualidades = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento ciencia = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento crime = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento diplomacia = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento enganacao = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento fortitude = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento furtividade = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento iniciativa = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento intimidacao = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento intuicao = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento investigacao = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento luta = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento medicina = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento ocultismo = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento percepcao = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento pilotagem = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento pontaria = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento profissao = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento reflexos = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento religiao = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento sobrevivencia = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento tatica = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento tecnologia = GrauTreinamento.NENHUM;

    @Enumerated(EnumType.STRING)
    private GrauTreinamento vontade = GrauTreinamento.NENHUM;
}