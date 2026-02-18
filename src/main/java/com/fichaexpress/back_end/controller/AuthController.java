package com.fichaexpress.back_end.controller;

import com.fichaexpress.back_end.dto.LoginResponseDTO;
import com.fichaexpress.back_end.dto.UserDTO;
import com.fichaexpress.back_end.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody UserDTO userDTO) {

        LoginResponseDTO reposta = userService.loginUser(userDTO);

        if(reposta != null ){
            return ResponseEntity.ok(reposta);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
}
