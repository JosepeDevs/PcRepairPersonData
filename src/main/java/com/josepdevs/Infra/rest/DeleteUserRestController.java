package com.josepdevs.Infra.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Application.DeleteUser;
import com.josepdevs.Domain.dto.UserDto;

@RestController //Spring will automatically send responses as JSON, no need to set that up
@RequestMapping("users")
public class DeleteUserRestController {

	//hacemos que el caso de uso sea un atributo del rest controller 
	private final DeleteUser deleteUser;
	
	@Autowired UserDto userDto;
    
	//inyectamos el caso de uso en el constructor 
	public DeleteUserRestController(DeleteUser deleteUser) {
		this.deleteUser = deleteUser;
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
		UUID idUser = UUID.fromString(id);
        deleteUser.deleteUser(idUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);

    }
	
	
	@DeleteMapping("hard/{id}")
    public ResponseEntity deleteHardUser(@PathVariable("id") String id) {
		UUID idUser = UUID.fromString(id);
        deleteUser.deleteHardUser(idUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(true);

    }

}
