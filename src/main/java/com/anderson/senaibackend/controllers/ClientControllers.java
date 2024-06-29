package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.dto.ClientDto;
import com.anderson.senaibackend.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cos/client")
public class ClientControllers {

    private final ClientService clientService;

    public ClientControllers(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<Client> findByCpf(@PathVariable(value = "cpf") String cpf){
        return ResponseEntity.ok().body(clientService.findByCpf(cpf));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Client> createClient(@RequestBody ClientDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(dto));
    }

    @PutMapping(value = "/update-client")
    public ResponseEntity<Client> updateClient(@RequestBody ClientDto dto){
        return ResponseEntity.ok().body(clientService.updateClient(dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id){
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
