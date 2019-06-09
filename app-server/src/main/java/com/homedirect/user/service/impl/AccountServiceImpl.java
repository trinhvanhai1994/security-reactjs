package com.homedirect.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.homedirect.user.entity.Account;
import com.homedirect.user.exception.ResourceNotFoundException;
import com.homedirect.user.model.Profile;
import com.homedirect.user.repository.AccountRepository;
import com.homedirect.user.service.AccountService;
import com.homedirect.user.transformer.ProfileTransformer;

public class AccountServiceImpl implements AccountService {

	private @Autowired ProfileTransformer profileTransformer;
	private @Autowired AccountRepository repo;
	
	private Account get(String username) {
		Account account = repo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		
		return account;
	}
	
	@Override
	public Profile getProfile(String username) throws Exception {
		return profileTransformer.toModel(get(username));
	}
}
