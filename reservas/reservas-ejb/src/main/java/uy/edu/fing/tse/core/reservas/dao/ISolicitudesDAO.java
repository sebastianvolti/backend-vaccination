package uy.edu.fing.tse.core.reservas.dao;

import java.util.List;

import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;

public interface ISolicitudesDAO {
	void agregarSolicitud(SolicitudDTO solicitud);
	void actualizarSolicitud(SolicitudDTO solicitud);
	List<SolicitudDTO> buscarSolicitud(String cedula);
	List<SolicitudDTO> getSolicitudesPendientesPorAgendaYEstado(Long idAgenda, String status);
	void eliminarSolicitudByCodigo(String codigo);
}
