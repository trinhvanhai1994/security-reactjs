package com.homedirect.user.model;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpRequest {
	
    @Size(min = 1, max = 40)
    @NotNull(message = "account.name.not-null")
    private String name;
    
    @Size(min = 1, max = 40)
    @NotNull(message = "account.phone.not-null")
    private String phone;
    
    @DecimalMax("3000000000.00")
    @NotNull(message = "account.salary.not-null")
    private BigDecimal salary;

    @Size(min = 3, max = 15)
    @NotNull(message = "account.username.not-null")
    private String username;
    
    private String roleName;

    @Email
    @Size(max = 40)
    @NotNull(message = "account.email.not-null")
    private String email;

    @Size(min = 6, max = 20)
    @NotNull(message = "account.password.not-null")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
