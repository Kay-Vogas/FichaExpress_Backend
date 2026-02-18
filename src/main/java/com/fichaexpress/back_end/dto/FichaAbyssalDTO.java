package com.fichaexpress.back_end.dto;


import com.fichaexpress.back_end.entities.ClassesOrdemParanormal;
import com.fichaexpress.back_end.entities.FichaAbyssal;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class FichaAbyssalDTO {

    private Long id;
    private String imagemPersonagem;
    private String personagem;


    private String origem;

    @Enumerated(EnumType.STRING)
    private ClassesOrdemParanormal classes;

    private Integer nex;
    private Integer ne;
    

    public FichaAbyssalDTO(){}

    public FichaAbyssalDTO(FichaAbyssal fichaAbyssal) {
        BeanUtils.copyProperties(fichaAbyssal, this);
    }

}
