package com.homedirect.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homedirect.user.entity.Employee;
import com.homedirect.user.model.EmployeeModel;
import com.homedirect.user.model.SignUpRequest;

@Service
public interface EmployeeService {

    List<EmployeeModel> all();
    Employee create(SignUpRequest request);
    Employee update(Employee entity, EmployeeModel model);
}
