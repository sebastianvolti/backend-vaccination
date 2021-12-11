package uy.edu.fing.tse.core.reservas.service;

import java.util.List;

import uy.edu.fing.tse.core.reservas.dto.ResultSolicitudes;
import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;

public interface ISolicitudesEJB {
	public void agregarSolicitud(SolicitudDTO s);

	public List<SolicitudDTO> buscarSolicitud(String cedula);

	ResultSolicitudes getSolicitudesPendientesPorAgendaYEstado(Long idAgenda, String status);

	void cancelarSolicitud(String codigo);

}
