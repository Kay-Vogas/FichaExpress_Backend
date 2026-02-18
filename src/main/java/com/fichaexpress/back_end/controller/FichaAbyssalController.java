package com.fichaexpress.back_end.controller;

import com.fichaexpress.back_end.entities.FichaAbyssal;
import com.fichaexpress.back_end.services.FichaAbyssalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ficha-abyssal")
@CrossOrigin(origins = "*")

public class FichaAbyssalController {

    @Autowired
    private FichaAbyssalService fichaAbyssalService;

    @GetMapping("/{id}")
    public FichaAbyssal buscarFichaAbyssalPorId(@PathVariable Long id){
        return fichaAbyssalService.buscarFichaAbyssalPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FichaAbyssal criarFichas(@RequestBody FichaAbyssal fichaAbyssal){
        return fichaAbyssalService.createFichaAbyssal(fichaAbyssal);
    }

    //TERMINAR A ATUALIZAÇÃO

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FichaAbyssal atualizarFichaAbyssal(@RequestBody FichaAbyssal fichaAbyssal, @PathVariable Long id) {
        return fichaAbyssalService.atualizarFichaAbyssal(id,fichaAbyssal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarFichaAbyssal(@PathVariable Long id){
        fichaAbyssalService.deletarFichaAbyssal(id);
    }

}
