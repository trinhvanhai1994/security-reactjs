package com.homedirect.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homedirect.user.model.LoginRequest;
import com.homedirect.user.model.SignUpRequest;
import com.homedirect.user.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private @Autowired AuthService service;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest request) {
        return service.signIn(request);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest request) throws Exception {
        return service.signUp(request);
    }
}
