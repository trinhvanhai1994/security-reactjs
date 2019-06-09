package com.homedirect.user.service.impl;

import java.net.URI;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.homedirect.user.entity.Account;
import com.homedirect.user.entity.Role;
import com.homedirect.user.exception.AppException;
import com.homedirect.user.model.ApiResponse;
import com.homedirect.user.model.JwtAuthenticationResponse;
import com.homedirect.user.model.LoginRequest;
import com.homedirect.user.model.SignUpRequest;
import com.homedirect.user.repository.AccountRepository;
import com.homedirect.user.repository.RoleRepository;
import com.homedirect.user.security.JwtTokenProvider;
import com.homedirect.user.service.AuthService;
import com.homedirect.user.transformer.AccountTransformer;
import com.homedirect.user.util.RoleName;
import com.homedirect.user.validator.AccountValidator;

@Service
public class AuthServiceImpl implements AuthService {
	
	private @Autowired AuthenticationManager authenticationManager;
    private @Autowired AccountRepository accountRepository;
    private @Autowired AccountTransformer transformer;
    private @Autowired AccountValidator validator;
    
    private @Autowired RoleRepository roleRepository;
    private @Autowired PasswordEncoder passwordEncoder;
    private @Autowired JwtTokenProvider tokenProvider;
    

	@Override
	public ResponseEntity<?> signIn(LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@Override
	public ResponseEntity<?> signUp(SignUpRequest request) throws Exception {
		validator.validateCreate(request);
		
        Account account = transformer.toEntity(request);

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        account.setRoles(Collections.singleton(userRole));

        Account result = accountRepository.save(account);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
}
