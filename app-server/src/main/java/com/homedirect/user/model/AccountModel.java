package com.homedirect.user.model;

import java.util.Date;

public class AccountModel {
	
    private Long id;
    private String username;
    private String password;
    private String email;
    private EmployeeModel employee;
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public EmployeeModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}

	public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
