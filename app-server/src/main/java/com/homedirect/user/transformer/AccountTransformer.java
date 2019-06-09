package com.homedirect.user.transformer;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.homedirect.user.entity.Account;
import com.homedirect.user.model.SignUpRequest;

@Component
public class AccountTransformer {

	public Account toEntity(SignUpRequest request) {
		Account entity = new Account();
		
		entity.setUsername(request.getUsername());
		entity.setEmployeeId(request.getEmployeeId());
		entity.setEmail(request.getEmail());
		entity.setPassword(request.getPassword());
		entity.setCreated(new Date());
		
		return entity;
	}
}

