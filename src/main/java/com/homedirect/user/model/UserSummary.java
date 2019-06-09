package com.homedirect.user.model;

public class UserSummary {
	
    private Long id;
    private Long employeeId;
    private String username;

    public UserSummary(Long id, Long employeeId, String username) {
        this.id = id;
        this.username = username;
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
