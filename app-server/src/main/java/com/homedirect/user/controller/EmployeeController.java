//package com.homedirect.user.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.homedirect.user.model.EmployeeModel;
//import com.homedirect.user.service.EmployeeService;
//
//@RestController
//@RequestMapping("/api")
//public class EmployeeController {
//
//    private @Autowired EmployeeService service;
//
//    @GetMapping("/employees/all")
//    @PreAuthorize("hasRole('ADMIN')")
//    List<EmployeeModel> all(HttpServletRequest httpServletRequest) {
//        return service.all();
//    }
//}
