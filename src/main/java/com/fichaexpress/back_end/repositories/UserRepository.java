package com.fichaexpress.back_end.repositories;

import com.fichaexpress.back_end.dto.UserDTO;
import com.fichaexpress.back_end.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);


}
