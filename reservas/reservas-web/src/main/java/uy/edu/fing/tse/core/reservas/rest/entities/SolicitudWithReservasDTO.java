package uy.edu.fing.tse.core.reservas.rest.entities;

import java.util.List;

import uy.edu.fing.tse.core.reservas.dto.ReservaDTO;
import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;

public class SolicitudWithReservasDTO {
	private SolicitudDTO solicitud;

	private List<ReservaDTO> reservas;

	public SolicitudWithReservasDTO(SolicitudDTO solicitud, List<ReservaDTO> reservas) {
		super();
		this.solicitud = solicitud;
		this.reservas = reservas;
	}

	public SolicitudDTO getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(SolicitudDTO solicitud) {
		this.solicitud = solicitud;
	}

	public List<ReservaDTO> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<ReservaDTO> reservas) {
		this.reservas = reservas;
	}



}
