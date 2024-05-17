package com.josepdevs.Infra.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.GetUser;
import com.josepdevs.Domain.dto.UserEntity;

@RestController //Spring will automatically send responses as JSON, no need to set that up
@RequestMapping("users")
public class GetUserRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final GetUser getUser;
	
	@Autowired Request request;
    
	//inyectamos el caso de uso en el constructor 
	public GetUserRestController(GetUser getUser) {
		this.getUser = getUser; 
	}
	
    @GetMapping
    public List<ResponseEntity> getAll() {
    	return getUser.getAll();
    }
    
    @GetMapping(value = "get{id}")
    public UserEntity getClient(@PathVariable String id, @RequestBody Request request) {
    	//el controller llama al caso de uso
    	UUID idUser = UUID.fromString(id);
    	return getUser.getUser(idUser);
    }
	

}
