package com.homedirect.user.service;

import org.springframework.http.ResponseEntity;

import com.homedirect.user.model.LoginRequest;
import com.homedirect.user.model.SignUpRequest;

public interface AuthService {

	ResponseEntity<?> signIn(LoginRequest request);
	ResponseEntity<?> signUp(SignUpRequest request) throws Exception;
}
