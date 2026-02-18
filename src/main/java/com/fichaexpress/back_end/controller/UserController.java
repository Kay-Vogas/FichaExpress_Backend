package com.fichaexpress.back_end.controller;

import com.fichaexpress.back_end.dto.FichaAbyssalDTO;
import com.fichaexpress.back_end.entities.FichaAbyssal;
import com.fichaexpress.back_end.entities.User;
import com.fichaexpress.back_end.repositories.UserRepository;
import com.fichaexpress.back_end.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public User cadastrarUser(@RequestBody User user) {
        return userService.cadastrarUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarUser(@PathVariable Long id){
        userService.deletarUser(id);
    }

    //Listar a Ficha Completa

    @GetMapping("/{id}/fichas-abyssal")
    public List<FichaAbyssal> listarTodasFichaAbyssal(@PathVariable Long id){
        return userService.listarFichasAbyssal(id);
    }

    //Listar DTO de Ficha Abyssal

    @GetMapping("/{id}/fichas-abyssal-dto")
    public List<FichaAbyssalDTO> listarTodasFichaAbyssalDTO(@PathVariable Long id){
        return userService.listarFichaAbyssalDTO(id);
    }

}
