package com.josepdevs.Infra.input;

import java.util.List;

import com.josepdevs.Domain.dto.UserEntity;

public interface RestInputPort {

    public UserEntity createClient(String name, String country);

    public UserEntity getClient(String idUser);

    public List<UserEntity> getAll();
    
    public UserEntity deleteClient(String idUser);
    
	
}
