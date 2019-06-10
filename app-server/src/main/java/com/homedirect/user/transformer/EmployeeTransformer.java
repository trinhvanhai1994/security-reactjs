package com.homedirect.user.transformer;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.homedirect.user.entity.Employee;
import com.homedirect.user.model.EmployeeModel;
import com.homedirect.user.model.SignUpRequest;

@Component
public class EmployeeTransformer {

	public Employee toEntity(SignUpRequest model) {
		Employee entity = new Employee();

		entity.setName(model.getName());
		entity.setPhone(model.getPhone());
		entity.setSalary(model.getSalary());
		entity.setCreated(new Date());

		return entity;
	}

	public EmployeeModel toModel(Employee entity) {
		EmployeeModel model = new EmployeeModel();

		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setPhone(entity.getPhone());
		model.setSalary(entity.getSalary());
//		model.setCreated(entity.getCreated().getTime());

		return model;
	}

	public Employee update(EmployeeModel model) {
		Employee entity = new Employee();
		
		entity.setName(model.getName());
		entity.setPhone(model.getPhone());
		entity.setSalary(model.getSalary());
		
		return entity;
	}
}
