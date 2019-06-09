package com.homedirect.user.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.homedirect.user.entity.Role;
import com.homedirect.user.entity.User;
import com.homedirect.user.exception.AppException;
import com.homedirect.user.model.ApiResponse;
import com.homedirect.user.model.JwtAuthenticationResponse;
import com.homedirect.user.model.LoginRequest;
import com.homedirect.user.model.SignUpRequest;
import com.homedirect.user.repository.RoleRepository;
import com.homedirect.user.repository.UserRepository;
import com.homedirect.user.security.JwtTokenProvider;
import com.homedirect.user.util.RoleName;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private @Autowired AuthenticationManager authenticationManager;
    private @Autowired UserRepository userRepository;
    private @Autowired RoleRepository roleRepository;
    private @Autowired PasswordEncoder passwordEncoder;
    private @Autowired JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws Exception {
    	
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new Exception("Exist Username!");
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
        	throw new Exception("Exist Username!");
        }
        
        // Creating user's account
        User user = new User(signUpRequest.getEmployeeId(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }
}
