package com.homedirect.user.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homedirect.user.entity.Account;
import com.homedirect.user.entity.Employee;

public class AccountPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
    private Employee employee;
    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public AccountPrincipal(Long id, Employee employee, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.employee = employee;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static AccountPrincipal create(Account user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new AccountPrincipal(
                user.getId(),
                user.getEmployee(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
