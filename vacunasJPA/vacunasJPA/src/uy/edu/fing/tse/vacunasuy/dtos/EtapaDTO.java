package uy.edu.fing.tse.vacunasuy.dtos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;





public class EtapaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String etapaNombre;
	private List<String> enfermedades;
	private List<HorasDTO> horas;
	private String vacuna;
	public String getEtapaNombre() {
		
		return etapaNombre;
	}
	public void setEtapaNombre(String etapaNombre) {
		this.etapaNombre = etapaNombre;
	}
	public List<String> getEnfermedades() {
		return enfermedades;
	}
	public void setEnfermedades(List<String> enfermedades) {
		this.enfermedades = enfermedades;
	}
	public List<HorasDTO> getHoras() {
		return horas;
	}
	public void setHoras(List<HorasDTO> horas) {
		this.horas = horas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVacuna() {
		return vacuna;
	}
	public void setVacuna(String vacuna) {
		this.vacuna = vacuna;
	}
	
	
}