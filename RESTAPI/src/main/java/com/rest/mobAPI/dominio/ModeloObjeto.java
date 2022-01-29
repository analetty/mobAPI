package com.rest.mobAPI.dominio;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ModeloObjeto {
	
	private String nombre;
	private String accion;
	private Date fechaCreacion;

}
