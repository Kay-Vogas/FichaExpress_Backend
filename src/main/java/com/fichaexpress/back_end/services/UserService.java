package com.fichaexpress.back_end.services;

import com.fichaexpress.back_end.dto.FichaAbyssalDTO;
import com.fichaexpress.back_end.dto.LoginResponseDTO;
import com.fichaexpress.back_end.dto.UserDTO;
import com.fichaexpress.back_end.entities.FichaAbyssal;
import com.fichaexpress.back_end.entities.User;
import com.fichaexpress.back_end.repositories.FichaAbyssalRepository;
import com.fichaexpress.back_end.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FichaAbyssalRepository fichaAbyssalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Transactional
    public User cadastrarUser(User user){
            String senhaPura =  user.getPassword();

            try {

                String senhaCodificada = passwordEncoder.encode(senhaPura);
                user.setPassword(senhaCodificada);
                return userRepository.save(user);

            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
    }


    @Transactional(readOnly = true) // readOnly é mais rápido para login (apenas leitura)
    public LoginResponseDTO loginUser(UserDTO dadosLogin){

        User usuarioNoBanco = userRepository.findByEmail(dadosLogin.getEmail())
                .orElse(null);

        if(usuarioNoBanco == null){
            return null;
        }
        if (passwordEncoder.matches(dadosLogin.getPassword(), usuarioNoBanco.getPassword())) {

            return new LoginResponseDTO(
                    usuarioNoBanco.getId(),
                    usuarioNoBanco.getPlayer(),
                    "Login realizado com sucesso!"
            );
        }

        return null;
    }

    @Transactional
    public void deletarUser(Long id){
        try {
            userRepository.deleteById(id);
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    //Listar a Ficha Completa

    @Transactional
    public List<FichaAbyssal> listarFichasAbyssal(Long id){
          try {
              User user = userRepository.findById(id).orElse(null);
              return user.getFichaAbyssal();

          }catch (RuntimeException e){
              throw new RuntimeException(e);
          }
    }

    //Listar DTO de Ficha Abyssal

    @Transactional
    public List<FichaAbyssalDTO> listarFichaAbyssalDTO(Long id){
        try {
            User user = userRepository.findById(id).orElse(null);

            return user.getFichaAbyssal().stream().map(fichaAbyssal -> new FichaAbyssalDTO(fichaAbyssal)).toList();

        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
