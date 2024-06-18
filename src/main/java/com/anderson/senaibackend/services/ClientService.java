package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.repositories.ClientRepository;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {


    private final ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        return clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id não encontrado!"));
    }
}
