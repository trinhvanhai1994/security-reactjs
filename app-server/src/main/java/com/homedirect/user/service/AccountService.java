package com.homedirect.user.service;

import java.util.List;

import com.homedirect.user.model.AccountModel;
import com.homedirect.user.security.AccountPrincipal;

public interface AccountService {

	AccountModel getCurrentAccount(AccountPrincipal currentUser);
	AccountModel getProfile(String username) throws Exception;
	
	boolean existUsername(String username);
	boolean existEmail(String email);
	
	AccountModel edit(Long id, AccountModel model) throws Exception;
	void delete(Long id) throws Exception;
	List<AccountModel> all();
}
