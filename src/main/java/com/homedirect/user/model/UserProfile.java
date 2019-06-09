package com.homedirect.user.model;

import java.util.Date;

public class UserProfile {
    private Long id;
    private Long employeeId;
    private String username;
    private Date created;

    public UserProfile(Long id, String username, Long employeeId, Date created) {
        this.id = id;
        this.username = username;
        this.employeeId = employeeId;
        this.created = created;
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

    public Date getJoinedAt() {
        return created;
    }

    public void setJoinedAt(Date created) {
        this.created = created;
    }
}
