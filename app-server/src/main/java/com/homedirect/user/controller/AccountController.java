package com.homedirect.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homedirect.user.model.AccountModel;
import com.homedirect.user.security.AccountPrincipal;
import com.homedirect.user.security.CurrentUser;
import com.homedirect.user.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

    private @Autowired AccountService service;

    @GetMapping("/user/profile")
    @PreAuthorize("hasRole('USER')")
    public AccountModel getCurrentUser(@CurrentUser AccountPrincipal currentUser) {
       return service.getCurrentAccount(currentUser);
    }

    @GetMapping("/user/duplicate-username")
    public boolean existUsername(@RequestParam String username) {
        return service.existUsername(username);
    }

    @GetMapping("/user/duplicate-email")
    public boolean existEmail(@RequestParam String email) {
        return service.existEmail(email);
    }

    @GetMapping("/users/{username}")
    public AccountModel getProfile(@PathVariable String username) throws Exception {
        return service.getProfile(username);
    }
    
    @PutMapping("/accounts/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public AccountModel update(@PathVariable Long id, @RequestBody AccountModel model) throws Exception {
    	return service.edit(id, model);
    }
    
    @DeleteMapping("/accounts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) throws Exception {
    	service.delete(id);
    }
    
    @GetMapping("/accounts/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountModel> all() {
    	return service.all();
    }
}
