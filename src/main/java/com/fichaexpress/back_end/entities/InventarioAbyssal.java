package com.fichaexpress.back_end.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable // Diz ao Spring: "Isso é parte de outra tabela"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventarioAbyssal {

    private String nome;
    private String categoria;
    private Integer espaco;
    private String descricao;
    private Integer bonusDoItem;
}
