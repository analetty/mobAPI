package com.rest.mobAPI.controlador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestControllerAdvice
public class ManejadorExcepciones {
	
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.class)
    public void indicarErroresPeticion(HttpServletResponse respuesta) throws IOException {
        respuesta.setContentType(MediaType.APPLICATION_JSON_VALUE);
        respuesta.setCharacterEncoding(StandardCharsets.UTF_8.displayName());

        final ObjectMapper mapper = new ObjectMapper();
        final ObjectNode json = mapper.createObjectNode();


        json.put("codigo","E0");
        json.put("mensaje","Objeto no encontrado");
        
        respuesta.getWriter().write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));

    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void indicarErroresCuerpoPeticion(HttpServletResponse respuesta) throws IOException {
        respuesta.setContentType(MediaType.APPLICATION_JSON_VALUE);
        respuesta.setCharacterEncoding(StandardCharsets.UTF_8.displayName());

        final ObjectMapper mapper = new ObjectMapper();
        final ObjectNode json = mapper.createObjectNode();


        json.put("codigo","E1");
        json.put("mensaje","Acción no permitida");
        
        respuesta.getWriter().write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));

    }

}

