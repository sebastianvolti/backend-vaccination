package uy.edu.fing.tse.vacunasuy.dtos;


import java.io.Serializable;
import java.util.Date;



public class DisponibilidadDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idParaCancelarDisponibilidad;
    private Date diaHora;
	private String vacunatorioNombre;
	private String direccion;
	private String departamento;
	private Double latitud;
	private Double longitud;
	private String enfermedades;
	private String vacuna;
	private String etapaNombre;
	private String planNombre;
	private Integer dosisNumero;
	private String laboratorios;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getIdParaCancelarDisponibilidad() {
		return idParaCancelarDisponibilidad;
	}
	public void setIdParaCancelarDisponibilidad(Integer idParaCancelarDisponibilidad) {
		this.idParaCancelarDisponibilidad = idParaCancelarDisponibilidad;
	}
	public Date getDiaHora() {
		return diaHora;
	}
	public void setDiaHora(Date diaHora) {
		this.diaHora = diaHora;
	}
	public String getVacunatorioNombre() {
		return vacunatorioNombre;
	}
	public void setVacunatorioNombre(String vacunatorioNombre) {
		this.vacunatorioNombre = vacunatorioNombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	public String getEnfermedades() {
		return enfermedades;
	}
	public void setEnfermedades(String enfermedades) {
		this.enfermedades = enfermedades;
	}
	public String getVacuna() {
		return vacuna;
	}
	public void setVacuna(String vacuna) {
		this.vacuna = vacuna;
	}
	
	public String getEtapaNombre() {
		return etapaNombre;
	}
	public void setEtapaNombre(String etapaNombre) {
		this.etapaNombre = etapaNombre;
	}
	public String getPlanNombre() {
		return planNombre;
	}
	public void setPlanNombre(String planNombre) {
		this.planNombre = planNombre;
	}
	public Integer getDosisNumero() {
		return dosisNumero;
	}
	public void setDosisNumero(Integer dosisNumero) {
		this.dosisNumero = dosisNumero;
	}
	public String getLaboratorios() {
		return laboratorios;
	}
	public void setLaboratorios(String laboratorios) {
		this.laboratorios = laboratorios;
	}
	
}