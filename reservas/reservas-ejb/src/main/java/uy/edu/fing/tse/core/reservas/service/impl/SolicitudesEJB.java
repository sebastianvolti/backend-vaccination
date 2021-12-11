package uy.edu.fing.tse.core.reservas.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import uy.edu.fing.tse.core.reservas.dao.IReservaDAO;
import uy.edu.fing.tse.core.reservas.dao.ISolicitudesDAO;
import uy.edu.fing.tse.core.reservas.dto.ResultSolicitudes;
import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;
import uy.edu.fing.tse.core.reservas.enums.SolicitudStatus;
import uy.edu.fing.tse.core.reservas.service.ISolicitudesEJB;

@Stateless
public class SolicitudesEJB implements ISolicitudesEJB{

	private static Long SERIAL = 1l;

	@EJB
	ISolicitudesDAO solicitudesDAO;

	@EJB
	IReservaDAO reservasDAO;

	@Override
	public void agregarSolicitud(SolicitudDTO s) {
		this.procesarSolicitud(s);

		this.solicitudesDAO.agregarSolicitud(s);
	}

	private void procesarSolicitud(SolicitudDTO s) {
		s.setFecha(LocalDateTime.now());
		s.setStatus(SolicitudStatus.PENDING);
		s.setCodigo(this.generateRandomCode());
	}

	private String generateRandomCode() {
		Random r = new Random();
		char c1 = (char)(r.nextInt(26) + 'A');
		char c2 = (char)(r.nextInt(26) + 'A');
		char c3 = (char)(r.nextInt(26) + 'A');
		char c4 = (char)(r.nextInt(26) + 'A');


		StringBuilder sb = new StringBuilder();
		sb.append(c1)
		.append(c2)
		.append(c3)
		.append(String.format("%05d", SERIAL));

		SERIAL++;

		return sb.toString();
	}

	@Override
	public List<SolicitudDTO> buscarSolicitud(String cedula) {
		return this.solicitudesDAO.buscarSolicitud(cedula);
	}

	@Override
	public ResultSolicitudes getSolicitudesPendientesPorAgendaYEstado(Long idAgenda, String status) {
		return new ResultSolicitudes(this.solicitudesDAO.getSolicitudesPendientesPorAgendaYEstado(idAgenda, status));
	}

	@Override
	public void cancelarSolicitud(String codigo) {
		this.solicitudesDAO.eliminarSolicitudByCodigo(codigo);
		this.reservasDAO.eliminarReservasByCodigo(codigo);
	}
}
