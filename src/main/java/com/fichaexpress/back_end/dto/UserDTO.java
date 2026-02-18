package com.fichaexpress.back_end.dto;

import com.fichaexpress.back_end.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter @Setter
public class UserDTO {

    private Long id;
    private String email;
    private String password ;

    public UserDTO() {};

    public UserDTO(User user) {
        BeanUtils.copyProperties(user,this);
    }

}
