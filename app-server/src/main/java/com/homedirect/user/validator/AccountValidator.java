package com.homedirect.user.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homedirect.user.model.SignUpRequest;
import com.homedirect.user.repository.AccountRepository;

@Component
public class AccountValidator {
	
	private @Autowired AccountRepository repo;

	public void validateCreate(SignUpRequest request) throws Exception {
		if(repo.findByUsernameOrEmail(request.getUsername(), request.getEmail()).isPresent()) {
			throw new Exception("Exist Username or Email!");
		}
	}
}
