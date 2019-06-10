package com.homedirect.user.transformer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homedirect.user.entity.Account;
import com.homedirect.user.entity.Employee;
import com.homedirect.user.model.AccountModel;
import com.homedirect.user.model.SignUpRequest;
import com.homedirect.user.repository.AccountRepository;
import com.homedirect.user.security.AccountPrincipal;
import com.homedirect.user.service.EmployeeService;

@Component
public class AccountTransformer {
	
	private @Autowired EmployeeTransformer employeeTransformer;
	private @Autowired EmployeeService employeeService;
	private @Autowired AccountRepository repo;

	public Account toEntity(SignUpRequest request, Employee employee) {
		Account entity = new Account();

		entity.setUsername(request.getUsername());
		entity.setEmployee(employee);
		entity.setEmail(request.getEmail());
		entity.setPassword(request.getPassword());
		entity.setCreated(new Date());

		return entity;
	}
	
	public AccountModel toModel(Account entity) {
		AccountModel model = new AccountModel();

		model.setId(entity.getId());
		model.setUsername(entity.getUsername());
		model.setEmail(entity.getEmail());
		model.setPassword("**************");
		model.setEmployee(employeeTransformer.toModel(entity.getEmployee()));
		model.setCreated(entity.getCreated());

		return model;
	}

	public AccountModel accountSummary(AccountPrincipal request) {
		AccountModel model = new AccountModel();
		Account account = repo.findByUsername(request.getUsername()).get();

		model.setId(request.getId());
		model.setUsername(request.getUsername());
		model.setEmail(request.getEmail());
		model.setPassword("**************");
		model.setEmployee(employeeTransformer.toModel(account.getEmployee()));
		model.setCreated(account.getCreated());
		
		return model;
	}
	
	public Account update(AccountModel model, Account entity) {

		entity.setUsername(model.getUsername());
		entity.setEmployee(employeeService.update(model.getEmployee()));
		entity.setEmail(model.getEmail());
		entity.setPassword(model.getPassword());
		entity.setCreated(entity.getCreated());

		return entity;
	}
}

