package uy.edu.fing.tse.vacunasuy.ws.dto;

import java.util.Date;

import uy.edu.fing.tse.vacunasuy.entity.Persona;
import uy.edu.fing.tse.vacunasuy.entity.Solicitud;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;

public class AgendaSesionData {

	private Date fecha;
	private PersonaData persona;
	
	private Integer numeroDosis;
	private String codigoSolicitud;
	private String statusSolicitud;
	private String zonaSolicitud;
	private String turnoSolicitud;
	private String statusReserva;
	private Date fechaReserva;
	private String nombreVacuna;
	private Integer nroDosis;
	private VacunatorioData vacunatorio;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getNombreVacuna() {
		return nombreVacuna;
	}
	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}
	public Integer getNumeroDosis() {
		return numeroDosis;
	}
	public void setNumeroDosis(Integer numeroDosis) {
		this.numeroDosis = numeroDosis;
	}
	public String getCodigoSolicitud() {
		return codigoSolicitud;
	}
	public void setCodigoSolicitud(String codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}
	public String getStatusSolicitud() {
		return statusSolicitud;
	}
	public void setStatusSolicitud(String statusSolicitud) {
		this.statusSolicitud = statusSolicitud;
	}
	public String getZonaSolicitud() {
		return zonaSolicitud;
	}
	public void setZonaSolicitud(String zonaSolicitud) {
		this.zonaSolicitud = zonaSolicitud;
	}
	public String getTurnoSolicitud() {
		return turnoSolicitud;
	}
	public void setTurnoSolicitud(String turnoSolicitud) {
		this.turnoSolicitud = turnoSolicitud;
	}
	public String getStatusReserva() {
		return statusReserva;
	}
	public void setStatusReserva(String statusReserva) {
		this.statusReserva = statusReserva;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Integer getNroDosis() {
		return nroDosis;
	}
	public void setNroDosis(Integer nroDosis) {
		this.nroDosis = nroDosis;
	}
	public PersonaData getPersona() {
		return persona;
	}
	public void setPersona(PersonaData persona) {
		this.persona = persona;
	}
	public VacunatorioData getVacunatorio() {
		return vacunatorio;
	}
	public void setVacunatorio(VacunatorioData vacunatorio) {
		this.vacunatorio = vacunatorio;
	}
	

	
	
}
