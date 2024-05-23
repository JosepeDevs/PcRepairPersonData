package com.josepdevs.Domain.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class StringHashCheckerService {
	/**
	 * 
	 * @param enteredPassword password from the login attempt
	 * @param currentStoredHashedPassword from the database
	 * @return boolean
	 */
	 public boolean verifyPassword(String unhashedPsswrd, String currentStoredHashedPassword) {
	        return BCrypt.checkpw(unhashedPsswrd, currentStoredHashedPassword);
	    }
}
