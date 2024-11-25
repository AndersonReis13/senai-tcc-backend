package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.domain.model.enums.TypeEmployee;
import com.anderson.senaibackend.domain.repositories.EmployeeRepository;
import com.anderson.senaibackend.dto.EmployeeDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findByCpf(String cpf){
        checkCpfInDataBase(cpf);
        return employeeRepository.findByCpf(cpf);
    }

    public Employee createEmployee(EmployeeDto dto){

        if(dto.id() != null){
            throw new BadRequestFoundException("O id do client tem que ser null");
        }

        checkFieldEmailInDataBase(dto.email());// verificando se ja existe um email cadastrado

        if(dto.typeEmployee() == null){
            throw new BadRequestFoundException("typeEmployee não pode ser null");
        }

        checkEnumInField(dto.typeEmployee()); // verificando se exisite o tipo selecionado

        return employeeRepository.save(dto.toEntity(dto));
    }

    public Employee updateEmployeeDetails(EmployeeDto dto){

        employeeRepository.findById(dto.id())
                .orElseThrow(()-> new ResourceNotFoundException("Funcionario não encontrado"));

        if(dto.typeEmployee() == null){
            throw new BadRequestFoundException("typeEmployee não pode ser null");
        }

        checkEnumInField(dto.typeEmployee());

        return employeeRepository.save(dto.toEntity(dto));
    }

    public void deleteEmployee(String cpf){
        checkCpfInDataBase(cpf);
        var EmployeeDb = employeeRepository.findByCpf(cpf);
        Employee employee = EmployeeDb;

        employeeRepository.delete(employee);
    }

    public void checkFieldEmailInDataBase(String email){
        if(employeeRepository.existsByEmail(email)){
            throw new BadRequestFoundException("Ja existe um email como esse cadastrado no sistema");
        }
    }

    public void checkEnumInField(String status){
        if(!TypeEmployee.existsEnum(status)){
            throw new BadRequestFoundException("Não existe este tipo de empregador");
        }
    }

    public void checkCpfInDataBase(String cpf){
        if(employeeRepository.findByCpf(cpf) == null) {
            throw new BadRequestFoundException("Usuario não encontrado");
        }
    }

}
