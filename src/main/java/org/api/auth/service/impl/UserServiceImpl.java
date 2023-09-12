package org.api.auth.service.impl;


import lombok.AllArgsConstructor;
import org.api.auth.dto.AuthenticationRequest;
import org.api.auth.dto.AuthenticationResponse;
import org.api.auth.dto.UserBasicDto;
import org.api.auth.dto.UserDto;
import org.api.auth.exception.UserAlreadyExistsException;
import org.api.auth.exception.UserRegistrationException;
import org.api.auth.mapper.UserMapper;
import org.api.auth.model.Role;
import org.api.auth.model.UserModel;
import org.api.auth.repository.RoleRepository;
import org.api.auth.repository.UserRepository;
import org.api.auth.service.IUserService;
import org.api.auth.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
    //private final MessageSource message;


    @Autowired
    private JwtUtils jwtTokenUtils;

    @Autowired
    AuthenticationManager authenticationManager;


    public AuthenticationResponse generateToken(AuthenticationRequest authRequest) throws Exception {

        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
            AuthenticationResponse jwt = JwtUtils.createToken(userDetails);
            return jwt;
        } catch (BadCredentialsException e) {
            throw new Exception("incorrecto el mail o contraseña", e);
        }
    }


    @Override
    @Transactional
    public UserBasicDto signup(UserDto userDto) {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistsException("la cuenta ya existe");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByTipo("USER").get();
        userDto.setRole(role);
        UserModel entity = userMapper.userDTO2Entity(userDto);
        entity = userRepository.save(entity);
        if (entity == null) {
            throw new UserRegistrationException("No se pudo registrar");
        }

        return userMapper.userEntity2UserBasicDto(entity);
    }


    private boolean emailExists(String email) {

        return userRepository.findByEmail(email) != null;

    }
    @Override
    public List<UserBasicDto> getAllUsers(){

        List<UserModel> userModels =userRepository.findAll();
        return userModels
                .stream()
                .map(userMapper::userEntity2UserBasicDto)
                .collect(Collectors.toList());
    }

}
