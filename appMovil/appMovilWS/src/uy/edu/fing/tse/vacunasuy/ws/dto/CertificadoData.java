package uy.edu.fing.tse.vacunasuy.ws.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CertificadoData {

	private Integer id;
	private String nombrePlan;
	private String nombreVacuna;
	private List<String> enfermedades= new ArrayList<String>();
	private String nombreLaboratorio;
	private String estado;
	private List<DosisData> dosis= new ArrayList<DosisData>();
	private Date inmunizacionDesde;
	private Date inmunizacionHasta;
	public String getNombreVacuna() {
		return nombreVacuna;
	}
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}
	public List<String> getEnfermedades() {
		return enfermedades;
	}
	public void setEnfermedades(List<String> enfermedades) {
		this.enfermedades = enfermedades;
	}
	public String getNombreLaboratorio() {
		return nombreLaboratorio;
	}
	public void setNombreLaboratorio(String nombreLaboratorio) {
		this.nombreLaboratorio = nombreLaboratorio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<DosisData> getDosis() {
		return dosis;
	}
	public void setDosis(List<DosisData> dosis) {
		this.dosis = dosis;
	}
	public Date getInmunizacionDesde() {
		return inmunizacionDesde;
	}
	public void setInmunizacionDesde(Date inmunizacionDesde) {
		this.inmunizacionDesde = inmunizacionDesde;
	}
	public Date getInmunizacionHasta() {
		return inmunizacionHasta;
	}
	public void setInmunizacionHasta(Date inmunizacionHasta) {
		this.inmunizacionHasta = inmunizacionHasta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombrePlan() {
		return nombrePlan;
	}
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}
	
	
	
}
