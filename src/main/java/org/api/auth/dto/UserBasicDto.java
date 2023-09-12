package org.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Setter
@Getter
@AllArgsConstructor
public class UserBasicDto {
    private Long id;
    @Email
    private String email;
    private String lastName;
    private String firstName;

    private RoleDto role;

    public UserBasicDto() {

    }
    @Builder
    public UserBasicDto(String firstName, String lastName, String email, Long id,RoleDto role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.role =role;
    }
}
