package com.rest.mobAPI.controlador;

import java.util.Date;

import com.rest.mobAPI.dominio.ModeloObjeto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeticionRestMOB {
	
	private String nombre;
	private String accion;
	
	public ModeloObjeto obtenerObjeto() {
		return new ModeloObjeto(nombre, accion, new Date());
	}

}
