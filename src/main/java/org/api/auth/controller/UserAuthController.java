package org.api.auth.controller;


import org.api.auth.dto.AuthenticationRequest;
import org.api.auth.dto.AuthenticationResponse;
import org.api.auth.dto.UserBasicDto;
import org.api.auth.dto.UserDto;
import org.api.auth.model.Role;
import org.api.auth.model.UserModel;
import org.api.auth.service.CustomUserDetailsService;
import org.api.auth.service.IUserService;
import org.api.auth.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
        try {
            UserBasicDto user = userService.signup(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authRequest) throws Exception {

        return ResponseEntity.ok(userService.generateToken(authRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserBasicDto>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
