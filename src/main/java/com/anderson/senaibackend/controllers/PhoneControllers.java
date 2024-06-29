package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.dto.PhoneDto;
import com.anderson.senaibackend.services.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cos/phone")
public class PhoneControllers {
    private final PhoneService phoneService;

    public PhoneControllers(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Phone>> findAll(){
        return ResponseEntity.ok().body(phoneService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Phone> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(phoneService.findById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Phone>createPhone(@RequestBody PhoneDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.createPhone(dto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Phone> updatePhone(@RequestBody PhoneDto dto){
        return ResponseEntity.ok().body(phoneService.updatePhone(dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id){
        phoneService.deletePhone(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
