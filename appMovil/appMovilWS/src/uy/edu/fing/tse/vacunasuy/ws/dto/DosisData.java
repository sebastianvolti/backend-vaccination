package uy.edu.fing.tse.vacunasuy.ws.dto;

import java.util.Date;
import java.util.List;

public class DosisData {

	private Integer numDosis;
	
	private Date fecha;
	private String vacunatorioNombre;
	public Integer getNumDosis() {
		return numDosis;
	}
	public void setNumDosis(Integer numDosis) {
		this.numDosis = numDosis;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getVacunatorioNombre() {
		return vacunatorioNombre;
	}
	public void setVacunatorioNombre(String vacunatorioNombre) {
		this.vacunatorioNombre = vacunatorioNombre;
	}
	
	
	
	
}
