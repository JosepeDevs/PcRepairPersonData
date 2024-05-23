package com.josepdevs.Domain.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class StringHasherService {
	    public  String hashPassword(String thingToHash) {
	        // Generate a salt, this way even if two persons has the same thingToHash it will not have the same final hash
	        String salt = BCrypt.gensalt();
	        
	        // Hash the password along with the salt
	        String hashedPassword = BCrypt.hashpw(thingToHash, salt);
	        
	        return hashedPassword;
	}
}
