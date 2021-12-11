package uy.edu.fing.tse.vacunasuy.ws.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PuestoVacunacionData {

	private Integer id;
	private String puesto;
	private Date fecha;

	
	private List<VacunadorAsignadoData> vacunadoresAsignados= new ArrayList<VacunadorAsignadoData>();



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}






	public List<VacunadorAsignadoData> getVacunadoresAsignados() {
		return vacunadoresAsignados;
	}



	public void setVacunadoresAsignados(List<VacunadorAsignadoData> vacunadoresAsignados) {
		this.vacunadoresAsignados = vacunadoresAsignados;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public String getPuesto() {
		return puesto;
	}



	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}


}
