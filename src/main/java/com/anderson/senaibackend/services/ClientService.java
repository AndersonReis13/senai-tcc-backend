package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.domain.repositories.ClientRepository;
import com.anderson.senaibackend.domain.repositories.PhoneRepository;
import com.anderson.senaibackend.dto.ClientDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
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

    public Client findByCpf(String cpf){
        logger.info("finding one client");
        checkCpfInDatabase(cpf);
        return clientRepository.findByCpf(cpf);
    }

    public Client createClient(ClientDto dto){
        if(dto.id() != null){
            throw new BadRequestFoundException("O id do client tem que ser null");
        }
        var phoneDb = phoneRepository.findById(dto.phoneId())
                .orElseThrow(()-> new ResourceNotFoundException("Esse celular não existe"));

        checkFieldInClientDataBase(dto.email(), dto.cpf());

        return clientRepository.save(dto.toEntity(dto, phoneDb));
    }

    public Client updateClientDetails(ClientDto dto){
        var phoneDb = phoneRepository.findById(dto.phoneId())
                .orElseThrow(() -> new ResourceNotFoundException("esse celular não existe"));

        var entityClient = clientRepository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("esse cliente não existe cadastrado"));

        return clientRepository.save(dto.toEntity(dto, phoneDb));
    }

    public void deleteEmployee(Long id){
        var clientDb = clientRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("o client não foi encontrado"));
        clientRepository.delete(clientDb);
    }

    public void checkFieldInClientDataBase(String email, String cpf){
        if (clientRepository.existsByEmailOrCpf(email, cpf)){
          throw new BadRequestFoundException("já existe um email ou cpf como esse cadastrado no sistema");
        }
    }

    public void checkEmailInDatabase(String email){
        if(!clientRepository.existsByEmail(email)){
            throw new ResourceNotFoundException("Email não encontrado");
        }
    }

    public void checkCpfInDatabase(String cpf){
        if(!clientRepository.existsByCpf(cpf)){
            throw new ResourceNotFoundException("cpf não encontrado");
        }
    }

}
