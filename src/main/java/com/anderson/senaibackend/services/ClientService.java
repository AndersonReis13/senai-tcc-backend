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
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;

    private Logger logger = Logger.getLogger(Client.class.getName());


    public ClientService(ClientRepository clientRepository, PhoneRepository phoneRepository){
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
    }

    public List<Client> findAll(){
        logger.info("finding all client");
        return clientRepository.findAll();
    }

    public Client findById(Long id){
        logger.info("finding one client");

        return clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("id não encontrado!"));
    }

    public Client createClient(ClientDto dto){

        if(Objects.nonNull(dto.phoneId())){
            new BadRequestFoundException("o id do celular está nulo");
        }

        var entityPhone = phoneRepository.findById(dto.phoneId())
                .orElseThrow(()-> new ResourceNotFoundException("Esse phone não existe"));

        Phone phone = entityPhone;

        System.out.println(phone);
        checkFieldInClientDataBase(dto.email(), dto.cpf());

        return clientRepository.save(ClientDtoToEntity.toEntity(dto, phone));
    }

    public void checkFieldInClientDataBase(String email, String cpf){
        if (clientRepository.existsByEmailOrCpf(email, cpf)) {
          throw new BadRequestFoundException("já existe um email ou cpf como esse cadastrado no sistema");
        }
    }


}
