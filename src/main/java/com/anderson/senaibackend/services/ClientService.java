package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.domain.repositories.ClientRepository;
import com.anderson.senaibackend.domain.repositories.PhoneRepository;
import com.anderson.senaibackend.dto.ClientDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import com.anderson.senaibackend.mapper.ClientMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Client findByEmail(String email){
        logger.info("finding one client");

        checkEmailInDatabe(email);
        return clientRepository.findByEmail(email);
    }

    public Client findByCpf(String cpf){
        logger.info("finding one client");

        checkCpfInDatabe(cpf);
        return clientRepository.findByCpf(cpf);
    }

    public Client createClient(ClientDto dto){

        var phoneDb = phoneRepository.findById(dto.phoneId())
                .orElseThrow(()-> new ResourceNotFoundException("Esse celular não existe"));

        Phone phone = phoneDb;

        checkFieldInClientDataBase(dto.email(), dto.cpf());

        return clientRepository.save(ClientMapper.toEntity(dto, phone));
    }

    public Client updateClient(ClientDto dto){
        var phoneDb = phoneRepository.findById(dto.phoneId())
                .orElseThrow(() -> new ResourceNotFoundException("esse celular não existe"));

        Phone phone = phoneDb;

        var entityClient = clientRepository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("esse cliente não existe cadastrado"));
                return clientRepository.save(ClientMapper.toEntity(dto, phone));
    }

    public void deleteClient(Long id){
        var ClientDb = clientRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("o client não foi encontrado"));
        Client client = ClientDb;
        clientRepository.delete(client);
    }

    public void checkFieldInClientDataBase(String email, String cpf){
        if (clientRepository.existsByEmailOrCpf(email, cpf)){
          throw new BadRequestFoundException("já existe um email ou cpf como esse cadastrado no sistema");
        }
    }

    public void checkEmailInDatabe(String email){
        if(clientRepository.existsByEmail(email)){
            throw new ResourceNotFoundException("Email não encontrado");
        }
    }

    public void checkCpfInDatabe(String cpf){
        if(clientRepository.existsByEmail(cpf)){
            throw new ResourceNotFoundException("cpf não encontrado");
        }
    }


}
