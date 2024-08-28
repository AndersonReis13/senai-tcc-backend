package com.anderson.senaibackend.infra.security;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    public String generateToken(Employee employee){
        try{
            //Algoritmo que vai gerar o hash do token
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            String token = JWT.create()
                    .withIssuer("employee-auth-api") // identificação para quem gerou o token
                    .withSubject(employee.getEmail()) // Salva um campo do usuario no token
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm); // para gerar o token precisa do hash
            return token;
        }catch (JWTCreationException ex){
            throw new BadRequestFoundException("Error ao gerar o token");
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("employee-auth-api")
                    .build()
                    .verify(token) //verificando se o token é valido
                      .getSubject(); // pegando o email que foi passado no metodo de gerar
        }catch (JWTVerificationException ex){
            return null;
        }
    }


    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}


