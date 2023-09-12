package org.api.auth.service;

import org.api.auth.dto.AuthenticationRequest;
import org.api.auth.dto.AuthenticationResponse;
import org.api.auth.dto.UserDto;
import org.api.auth.dto.UserBasicDto;
import org.api.auth.model.Role;
import org.api.auth.model.UserModel;

import java.util.List;


public interface IUserService {

    UserBasicDto signup(UserDto userDto) ;

    AuthenticationResponse generateToken(AuthenticationRequest authRequest) throws Exception;

    List<UserBasicDto> getAllUsers();


}
