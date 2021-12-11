package uy.edu.fing.tse.core.reservas.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import uy.edu.fing.tse.core.reservas.enums.ReservaStatus;

public class ReservaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer dosis;

	private SolicitudDTO solicitud;

	private Long vacunatorioId;

	private LocalDateTime fecha;

	private ReservaStatus status;


	public ReservaDTO() {
		super();
	}

	public ReservaDTO(Integer dosis, SolicitudDTO solicitud, Long vacunatorioId, LocalDateTime fecha, ReservaStatus status) {
		super();
		this.dosis = dosis;
		this.solicitud = solicitud;
		this.vacunatorioId = vacunatorioId;
		this.fecha = fecha;
		this.status = status;
	}

	public Integer getDosis() {
		return this.dosis;
	}

	public void setDosis(Integer dosis) {
		this.dosis = dosis;
	}

	public SolicitudDTO getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(SolicitudDTO solicitud) {
		this.solicitud = solicitud;
	}

	public Long getVacunatorioId() {
		return this.vacunatorioId;
	}

	public void setVacunatorioId(Long vacunatorioId) {
		this.vacunatorioId = vacunatorioId;
	}

	public LocalDateTime getFecha() {
		return this.fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public ReservaStatus getStatus() {
		return this.status;
	}

	public void setStatus(ReservaStatus status) {
		this.status = status;
	}


}
