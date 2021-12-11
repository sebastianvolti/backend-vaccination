package uy.edu.fing.tse.core.reservas.dto;

import java.util.List;


public class ResultSolicitudes {

	private List<SolicitudDTO> solicitudes;

	public ResultSolicitudes(List<SolicitudDTO> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public List<SolicitudDTO> getSolicitudes() {
		return this.solicitudes;
	}

	public void setSolicitudes(List<SolicitudDTO> solicitudes) {
		this.solicitudes = solicitudes;
	}


}
