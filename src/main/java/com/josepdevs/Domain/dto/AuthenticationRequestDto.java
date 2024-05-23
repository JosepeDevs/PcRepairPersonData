package com.josepdevs.Domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Class used to transport Email and Psswrd 
 */
//@Data // generates getters and setters for all fields, toString method, and hashCode and equals implementations that check all non-transient fields

@Getter
@NoArgsConstructor
public class AuthenticationRequestDto {

    private transient String  Email;
    private transient String  psswrd;
}