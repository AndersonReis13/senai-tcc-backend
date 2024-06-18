package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "cos/v1")
public class ClientControllers {

    private final ClientService clientService;

    public ClientControllers(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Client> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }
}
