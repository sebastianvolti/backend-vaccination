package uy.edu.fing.tse.core.reservas.dao.impl;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ejb.Stateless;

import com.mongodb.client.MongoCollection;

import uy.edu.fing.tse.core.reservas.dao.ISolicitudesDAO;
import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;
import uy.edu.fing.tse.core.reservas.enums.SolicitudStatus;

@Stateless
public class SolicitudesDAO implements ISolicitudesDAO {

	@Override
	public void agregarSolicitud(SolicitudDTO solicitud) {
		MongoCollection<SolicitudDTO> collection = MongoConnection.getInstance().getSolicitudesCollection();

		collection.insertOne(solicitud);

		System.out.println("Solicitud: CODIGO=" + solicitud.getCodigo() + ", CEDULA=" + solicitud.getCi());
	}

	@Override
	public void actualizarSolicitud(SolicitudDTO solicitud) {

		MongoCollection<SolicitudDTO> collection = MongoConnection.getInstance().getSolicitudesCollection();

		collection.findOneAndReplace(and(eq("ci", solicitud.getCi()), eq("idAgenda", solicitud.getIdAgenda())), solicitud);

		System.out.println("Solicitud UPDATE: CEDULA=" + solicitud.getCi() + ", AGENDA=" + solicitud.getIdAgenda());
	}

	@Override
	public List<SolicitudDTO> buscarSolicitud(String cedula) {
		try {
			MongoCollection<SolicitudDTO> collection = MongoConnection.getInstance().getSolicitudesCollection();

			List<SolicitudDTO> solicitudes = new ArrayList<>();

			Consumer<SolicitudDTO> consumer = solicitud -> solicitudes.add(solicitud);


			collection.find(eq("ci", cedula)).forEach(consumer);

			return solicitudes;

		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SolicitudDTO> getSolicitudesPendientesPorAgendaYEstado(Long idAgenda, String status){
		try {

			SolicitudStatus enumStatus = SolicitudStatus.findByString(status);

			if(enumStatus == null) {
				throw new Exception("Invalid status");
			}

			MongoCollection<SolicitudDTO> collection = MongoConnection.getInstance().getSolicitudesCollection();
			List<SolicitudDTO> result = new ArrayList<>();

			Consumer<SolicitudDTO> consumer = solicitud -> result.add(solicitud);

			collection.find(and(eq("idAgenda", idAgenda), eq("status", enumStatus.name()))).forEach(consumer);

			System.out.println("RESULT: " + result + ", size: " + result.size());

			return result;

		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public void eliminarSolicitudByCodigo(String codigo){
		try {
			MongoCollection<SolicitudDTO> collection = MongoConnection.getInstance().getSolicitudesCollection();

			collection.deleteOne(eq("codigo", codigo));

		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}



}
