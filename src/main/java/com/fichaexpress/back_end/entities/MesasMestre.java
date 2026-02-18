package com.fichaexpress.back_end.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//@Entity
public class MesasMestre {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private FichaAbyssal fichaAbyssal;
}
