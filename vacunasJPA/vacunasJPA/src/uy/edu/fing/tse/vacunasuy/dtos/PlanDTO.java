package uy.edu.fing.tse.vacunasuy.dtos;

import java.io.Serializable;

import java.util.List;





public class PlanDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String planNombre;
	private List<EtapaDTO> etapas;
	public String getPlanNombre() {
		return planNombre;
	}
	public void setPlanNombre(String planNombre) {
		this.planNombre = planNombre;
	}
	public List<EtapaDTO> getEtapas() {
		return etapas;
	}
	public void setEtapas(List<EtapaDTO> etapas) {
		this.etapas = etapas;
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
	
}