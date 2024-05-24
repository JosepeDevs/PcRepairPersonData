package com.josepdevs.Domain.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PersonDataDto {
	
    private String name;
    private String nidPassport;
    private String role;
    private boolean active;
    
}
