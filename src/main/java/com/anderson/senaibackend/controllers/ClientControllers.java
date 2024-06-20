package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.dto.ClientDto;
import com.anderson.senaibackend.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cos/")
public class ClientControllers {

    private final ClientService clientService;

    public ClientControllers(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "client/all")
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping(value = "client/{id}")
    public ResponseEntity<Client> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @PostMapping(value = "client/create-client")
    public ResponseEntity<Client> createClient(@RequestBody ClientDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(dto));
    }
}
