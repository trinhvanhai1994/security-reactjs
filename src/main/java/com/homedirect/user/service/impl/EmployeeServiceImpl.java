package com.homedirect.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homedirect.user.entity.Employee;
import com.homedirect.user.model.EmployeeModel;
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
    public EmployeeModel create(EmployeeModel model) {
        Employee entity = transformer.toEntity(model);
        repo.save(entity);
        return transformer.toModel(entity);
    }

    @Override
    public EmployeeModel get(Long id) {
        Employee entity = repo.findById(id).get();
        return transformer.toModel(entity);
    }

    @Override
    public EmployeeModel update(Long id, EmployeeModel model) {
        Employee entity = repo.findById(id).get();
        transformer.update(entity, model);
        return transformer.toModel(entity);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
