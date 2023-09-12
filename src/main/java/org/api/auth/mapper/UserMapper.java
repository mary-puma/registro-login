package org.api.auth.mapper;


import org.api.auth.dto.RoleDto;
import org.api.auth.dto.UserBasicDto;
import org.api.auth.dto.UserDto;
import org.api.auth.model.Role;
import org.api.auth.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel userDTO2Entity(UserDto dto) {
        UserModel entity = new UserModel();
        entity.setFirstname(dto.getFirstName());
        entity.setLastname(dto.getLastName());
        entity.setPassword(dto.getPassword());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());

        return entity;
    }

    public UserBasicDto userEntity2UserBasicDto(UserModel entity) {
        UserBasicDto userBasicDto = new UserBasicDto();
        userBasicDto.setEmail(entity.getEmail());
        userBasicDto.setFirstName(entity.getFirstname());
        userBasicDto.setLastName(entity.getLastname());
        userBasicDto.setId(entity.getId());
        Role role = entity.getRole();
        userBasicDto.setRole(new RoleDto(role.getTipo()));
        return userBasicDto;

    }


}


