package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.OS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "cos/os")
public class OSControllers {

    public ResponseEntity<List<OS>> findAll(){
        return
    }


}
