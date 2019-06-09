package com.homedirect.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homedirect.user.model.EmployeeModel;
import com.homedirect.user.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private @Autowired EmployeeService service;

    @GetMapping("/employees/all")
    @PreAuthorize("hasRole('ADMIN')")
    List<EmployeeModel> all(HttpServletRequest httpServletRequest) {
        return service.all();
    }

    @PostMapping
    EmployeeModel create(@RequestBody EmployeeModel model, HttpServletRequest HttpServletRequest) {
        return service.create(model);
    }

    @GetMapping("/{id}")
    EmployeeModel get(@PathVariable Long id, HttpServletRequest httpServletRequest) throws Exception {
        return service.get(id);
    }

    @PutMapping("/{id}")
    EmployeeModel update(@PathVariable Long id, @RequestBody EmployeeModel model, HttpServletRequest httpServletRequest) {
        return service.update(id, model);
    }

    @DeleteMapping ("/{id}")
    void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
