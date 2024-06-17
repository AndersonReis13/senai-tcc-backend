package com.anderson.senaibackend.exceptions.handle;

import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustumizedExceptionHandle {

     @ExceptionHandler(Exception.class)
    public final ResponseEntity<ModelExceptions> handleAllExcepttions(Exception ex, WebRequest request){
         ModelExceptions modelExceptions = new ModelExceptions(
                 ex.getMessage(),
                 request.getDescription(false),
                 HttpStatus.INTERNAL_SERVER_ERROR,
                 HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                 new Date()
         );

         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(modelExceptions);
    }

    @ExceptionHandler(BadRequestFoundException.class)
    public final ResponseEntity<ModelExceptions> badRequestExceptionHandle(BadRequestFoundException bad, WebRequest request ){
         ModelExceptions modelExceptions = new ModelExceptions(
           bad.getMessage(),
           request.getDescription(false),
           HttpStatus.BAD_REQUEST,
           HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()),
           new Date()
         );
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelExceptions);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ModelExceptions> resourceNotFoundExceptionHandle(ResourceNotFoundException resource, WebRequest request){
         ModelExceptions modelExceptions = new ModelExceptions(
                 resource.getMessage(),
                 request.getDescription(false),
                 HttpStatus.NOT_FOUND,
                 HttpStatus.valueOf(HttpStatus.NOT_FOUND.value()),
                 new Date()
         );
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(modelExceptions);
    }
}
