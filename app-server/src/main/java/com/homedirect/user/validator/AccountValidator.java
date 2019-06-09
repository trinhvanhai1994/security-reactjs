package com.homedirect.user.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homedirect.user.model.SignUpRequest;
import com.homedirect.user.repository.AccountRepository;

@Component
public class AccountValidator {
	
	private @Autowired AccountRepository repo;

	public void validateCreate(SignUpRequest request) throws Exception {
		if(repo.existsByUsername(request.getUsername())) {
            throw new Exception("Exist Username!");
        }

        if(repo.existsByEmail(request.getEmail())) {
        	throw new Exception("Exist Username!");
        }
	}
}
