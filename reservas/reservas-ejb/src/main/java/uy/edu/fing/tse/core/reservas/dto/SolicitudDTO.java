package uy.edu.fing.tse.core.reservas.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import uy.edu.fing.tse.core.reservas.enums.Departamento;
import uy.edu.fing.tse.core.reservas.enums.Horario;
import uy.edu.fing.tse.core.reservas.enums.SolicitudStatus;

public class SolicitudDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;
	private Long idAgenda;
	private String ci;
	private String email;
	private LocalDateTime fecha;
	private SolicitudStatus status;
	private Departamento departamento;
	private Long idVacunatorio;
	private Horario horario;

	public SolicitudDTO() {

	}

	public SolicitudDTO(String codigo, Long idAgenda, String ci, String email, LocalDateTime fecha, SolicitudStatus status, Departamento departamento, Long idVacunatorio, Horario horario) {
		this.codigo = codigo;
		this.idAgenda = idAgenda;
		this.ci = ci;
		this.email = email;
		this.fecha = fecha;
		this.status = status;
		this.departamento = departamento;
		this.idVacunatorio = idVacunatorio;
		this.horario = horario;
	}


	public String getCodigo() {
		return this.codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Long getIdAgenda() {
		return this.idAgenda;
	}
	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}
	public String getCi() {
		return this.ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Departamento getDepartamento() {
		return this.departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Long getIdVacunatorio() {
		return this.idVacunatorio;
	}

	public void setIdVacunatorio(Long idVacunatorio) {
		this.idVacunatorio = idVacunatorio;
	}

	public Horario getHorario() {
		return this.horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}


	public LocalDateTime getFecha() {
		return this.fecha;
	}


	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	public SolicitudStatus getStatus() {
		return this.status;
	}


	public void setStatus(SolicitudStatus status) {
		this.status = status;
	}



}
