package com.homedirect.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homedirect.user.model.EmployeeModel;

@Service
public interface EmployeeService {

    List<EmployeeModel> all();
    EmployeeModel create(EmployeeModel model);
    EmployeeModel get(Long id) throws Exception;
    EmployeeModel update(Long id, EmployeeModel model);
    void delete(Long id);
}
