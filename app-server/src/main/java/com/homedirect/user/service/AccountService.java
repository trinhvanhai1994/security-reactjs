package com.homedirect.user.service;

import org.springframework.web.bind.annotation.PathVariable;

import com.homedirect.user.model.Profile;

public interface AccountService {

	Profile getProfile(@PathVariable String username) throws Exception;
}
