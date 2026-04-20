package com.siddhant.examportal.dto;

import com.siddhant.examportal.enums.Role;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
