package org.api.auth.repository;


import org.api.auth.dto.UserBasicDto;
import org.api.auth.dto.UserDto;
import org.api.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String email);

    //@Query(value = "SELECT new org.api.auth.dto.UserBasicDto(u.id, u.firstName, u.lastName, u.email) FROM UserModel u")
    //List<UserBasicDto> getAllUsers();

    @Query(value = "SELECT COUNT(id) FROM UserModel")
    int getUsersQuantity();

}
