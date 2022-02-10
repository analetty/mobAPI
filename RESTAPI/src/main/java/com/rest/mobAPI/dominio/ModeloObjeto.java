package com.rest.mobAPI.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ModeloObjeto {
	
	private String id;
	private String nombre;
	private String accion;
	private String fechaCreacion;

}
