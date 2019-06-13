package com.homedirect.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedirect.user.entity.Employee;
import com.homedirect.user.model.EmployeeModel;
import com.homedirect.user.model.SignUpRequest;
import com.homedirect.user.repository.EmployeeRepository;
import com.homedirect.user.service.EmployeeService;
import com.homedirect.user.transformer.EmployeeTransformer;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private @Autowired EmployeeRepository repo;
	private @Autowired EmployeeTransformer transformer;

	@Override
	public List<EmployeeModel> all() {
		return repo.findAll().stream().map(transformer::toModel).collect(Collectors.toList());
	}

	@Override
	public Employee create(SignUpRequest request) {
		Employee entity = transformer.toEntity(request);
		return repo.save(entity);
	}
	
	@Override
	public Employee update(Employee entity, EmployeeModel model) {
		transformer.update(entity, model);
		return repo.save(entity);
	}
}
