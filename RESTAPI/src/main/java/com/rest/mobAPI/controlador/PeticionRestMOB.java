package com.rest.mobAPI.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.validation.constraints.Pattern;

import com.rest.mobAPI.dominio.ModeloObjeto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeticionRestMOB {
	
	//TODO NOT BLANK
	private String nombre;
	@Pattern(regexp = "(CREAR|AZAR|COMMIT|ABORT)", message = "Debe indicar una accion")
	private String accion;
	
	private String generarID() {
		long now = System.currentTimeMillis();
		long randomLong = new Random().nextLong();
		return ("ID_"+now+"-"+randomLong);
	}
	
	public ModeloObjeto obtenerObjeto() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String date = simpleDateFormat.format(new Date());
		return new ModeloObjeto(generarID(), nombre, accion, date);
	}

}
