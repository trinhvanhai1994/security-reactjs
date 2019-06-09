package com.homedirect.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homedirect.user.model.Profile;
import com.homedirect.user.model.UserSummary;
import com.homedirect.user.repository.AccountRepository;
import com.homedirect.user.security.CurrentUser;
import com.homedirect.user.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getEmployeeId(), currentUser.getUsername());
        return userSummary;
    }

    @GetMapping("/user/duplicate-username")
    public boolean checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return userRepository.existsByUsername(username);
    }

    @GetMapping("/user/duplicate-email")
    public boolean checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userRepository.existsByEmail(email);
    }

    @GetMapping("/users/{username}")
    public Profile getProfile(@PathVariable String username) {
    	
//        Account user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
//
//        Profile profile = new Profile(user.getId(), user.getUsername(), user.getEmployeeId(), user.getCreated());

        return null;
    }
}
