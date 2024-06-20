package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.domain.repositories.ClientRepository;
import com.anderson.senaibackend.domain.repositories.PhoneRepository;
import com.anderson.senaibackend.dto.ClientDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import com.anderson.senaibackend.mapper.ClientDtoToEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;


    public ClientService(ClientRepository clientRepository, PhoneRepository phoneRepository){
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        return clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id não encontrado!"));
    }

    public Client createClient(ClientDto dto){

        var phoneId = phoneRepository.findById(dto.phoneId())
                .orElseThrow(()-> new ResourceNotFoundException("Esse phone não existe"));
        Phone phone = phoneId;

        checkFieldInClientDataBase(dto.email(), dto.cpf());

        return clientRepository.save(ClientDtoToEntity.toEntity(dto, phone));
    }

    public void checkFieldInClientDataBase(String email, String cpf){
        if (clientRepository.existsByEmailOrCpf(email, cpf)) {
          throw  new BadRequestFoundException("já existe um email ou cpf como esse cadastrado no sistema");
        }
    }


}
