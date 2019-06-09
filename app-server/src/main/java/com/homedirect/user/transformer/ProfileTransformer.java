package com.homedirect.user.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homedirect.user.entity.Account;
import com.homedirect.user.model.Profile;
import com.homedirect.user.service.EmployeeService;

@Component
public class ProfileTransformer {
	
	private @Autowired EmployeeService employeeservice; 
		
	public Profile toModel(Account account) throws Exception {
		Profile profile = new Profile();
		
		profile.setId(account.getId());
		profile.setEmployee(employeeservice.get(account.getEmployeeId()));
		profile.setUsername(account.getUsername());
		profile.setCreated(account.getCreated());
		
		return profile;
	}
}
