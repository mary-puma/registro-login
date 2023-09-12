package org.api.auth.dto;


import lombok.*;
import org.api.auth.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotNull(message="First name can't be null")
    private String firstName;

    @NotNull(message="Last name can't be null")
    private String lastName;

    @NotNull(message="Email can't be null")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message="Password can't be null")
    private String password;

    private Role role;


}
