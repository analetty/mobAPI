package com.rest.mobAPI.controlador;

import org.springframework.http.MediaType;

import java.io.IOException;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.rest.mobAPI.dominio.ModeloObjeto;
import com.rest.mobAPI.logica.LogicaObjeto;



@RestController
public class ControladorRestMOB {
	
	LogicaObjeto logica;
	private String id;
	
	@GetMapping(value = "/objeto", produces = MediaType.APPLICATION_JSON_VALUE)
	public String obtenerObjetos() throws Exception {
		logica = new LogicaObjeto();
		return logica.consultarObjetos();
	}
	
	@GetMapping(value = "/objeto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String obtenerObjeto(@PathVariable(name = "id") String id) throws Exception {
		ModeloObjeto peticion = new ModeloObjeto();
		peticion.setId(id);
		logica = new LogicaObjeto();
		return logica.consultarObjeto(peticion);
	}
	
	@PostMapping(value = "/objeto", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
	public String crearObjeto(
			 @RequestBody @Valid PeticionRestMOB datos) throws TransformerException, ParserConfigurationException, SAXException, IOException {
		logica = new LogicaObjeto();
		logica.crearObjeto(datos);
		return "Crear";
	}
	
	@DeleteMapping("/objeto/{id}")
	public String eliminarObjeto(@PathVariable(name = "id") String id) throws Exception {
		ModeloObjeto peticion = new ModeloObjeto();
		peticion.setId(id);
		logica = new LogicaObjeto();
		logica.eliminarObjeto(peticion);
		return "Eliminar";
	}
	
	@PostMapping(value = "/replicar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
	public String replicar(
			 @RequestBody @Valid PeticionRestMOB datos) {
		return "Replicar";
	}
	
	@PostMapping(value = "/restaurar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
	public String restaurar(
			 @RequestBody @Valid PeticionRestMOB datos) {
		return "Restaurar";
	}
	
	
	

}
