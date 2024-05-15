package com.josepdevs.Infra.input;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josepdevs.Domain.dto.UserEntity;

@RestController
@RequestMapping(value = "client")
public class ClientHttpInputAdapterController {

	@Autowired
    RestInputPort clientInputPort;

    @PostMapping(value = "create", produces=MediaType.APPLICATION_JSON_VALUE)
    public UserEntity create( @RequestParam String name, @RequestParam String country ) {
        return clientInputPort.createClient(name, country);
    }

    @PutMapping(value = "update/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public UserEntity get(@RequestParam(name="id") String customerId ) {
        return clientInputPort.getClient(customerId);
    }

    @GetMapping(value = "getall", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> getAll() {
    	return clientInputPort.getAll();
    }
	
    @GetMapping(value = "get{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<UserEntity> getClient(@RequestParam String id) {
    	return clientInputPort.getAll();
    }
	
}
