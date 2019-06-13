package com.homedirect.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedirect.user.entity.Account;
import com.homedirect.user.model.AccountModel;
import com.homedirect.user.repository.AccountRepository;
import com.homedirect.user.security.AccountPrincipal;
import com.homedirect.user.service.AccountService;
import com.homedirect.user.transformer.AccountTransformer;

import javassist.NotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

	private @Autowired AccountTransformer transformer;
	private @Autowired AccountRepository repo;
	
	private Account getByUsername(String username) throws NotFoundException {
		return repo.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Khong tim thay tai khoan voi ten: " + username));
	}
	
	private Account getById(Long id) throws NotFoundException {
		return repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Khong tim thay tai khoan voi ID: " + id));
	}
	
	@Override
	public AccountModel getProfile(String username) throws Exception {
		return transformer.toModel(getByUsername(username));
	}

	@Override
	public boolean existUsername(String username) {
		return repo.existsByUsername(username);
	}

	@Override
	public boolean existEmail(String email) {
		return repo.existsByEmail(email);
	}

	@Override
	public AccountModel getCurrentAccount(AccountPrincipal currentUser) {
		return transformer.accountSummary(currentUser);
	}

	@Override
	public AccountModel edit(Long id, AccountModel model) throws Exception {
		Account entity = getById(id);
		transformer.update(model, entity);
		repo.save(entity);
		return transformer.toModel(entity);
	}

	@Override
	public void delete(Long id) throws Exception {
		Account entity = getById(id);
		repo.delete(entity);
	}

	@Override
	public List<AccountModel> all() {
		return repo.findAllMember().stream().map(transformer::toModel).collect(Collectors.toList());
	}
}
