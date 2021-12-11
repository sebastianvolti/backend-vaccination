package uy.edu.fing.tse.vacunasuy.dtos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;





public class HorasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String hora;

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}