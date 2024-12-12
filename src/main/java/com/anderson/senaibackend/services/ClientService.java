package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.repositories.ClientRepository;
import com.anderson.senaibackend.domain.repositories.OsRepository;
import com.anderson.senaibackend.domain.repositories.PhoneRepository;
import com.anderson.senaibackend.dto.ClientDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.CPFException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;
    private final OsRepository osRepository;

    private Logger logger = Logger.getLogger(Client.class.getName());


    public ClientService(ClientRepository clientRepository, PhoneRepository phoneRepository, OsRepository osRepository){
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
        this.osRepository = osRepository;
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

        var osDb = osRepository.findById(dto.orderId())
                        .orElseThrow(()-> new ResourceNotFoundException("essa ordem não existe"));

        checkFieldInClientDataBase(dto.email(), dto.cpf());
        isValidCPF(dto.cpf());

        return clientRepository.save(dto.toEntity(dto, phoneDb, osDb));
    }

    public Client updateClientDetails(ClientDto dto){
        var phoneDb = phoneRepository.findById(dto.phoneId())
                .orElseThrow(() -> new ResourceNotFoundException("esse celular não existe"));

        var entityClient = clientRepository.findById(dto.id())
                .orElseThrow(() -> new ResourceNotFoundException("esse cliente não existe cadastrado"));

        var osDb = osRepository.findById(dto.orderId())
                .orElseThrow(()-> new ResourceNotFoundException("essa ordem não existe"));

        return clientRepository.save(dto.toEntity(dto, phoneDb,osDb));
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

    private void checkEmailInDatabase(String email){
        if(!clientRepository.existsByEmail(email)){
            throw new ResourceNotFoundException("Email não encontrado");
        }
    }

    private void checkCpfInDatabase(String cpf){
        if(!clientRepository.existsByCpf(cpf)){
            throw new ResourceNotFoundException("cpf não encontrado");
        }
    }

    private boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        // CPF com todos os números iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            throw new CPFException("O cpf contém todos os números iguais");
        }

        return false;
    }

}
