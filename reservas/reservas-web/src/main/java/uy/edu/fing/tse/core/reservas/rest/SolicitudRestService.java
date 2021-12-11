package uy.edu.fing.tse.core.reservas.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uy.edu.fing.tse.core.reservas.dto.ReservaDTO;
import uy.edu.fing.tse.core.reservas.dto.ResultSolicitudes;
import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;
import uy.edu.fing.tse.core.reservas.enums.SolicitudStatus;
import uy.edu.fing.tse.core.reservas.rest.entities.SolicitudWithReservasDTO;
import uy.edu.fing.tse.core.reservas.service.IReservasEJB;
import uy.edu.fing.tse.core.reservas.service.ISolicitudesEJB;

@Path("/solicitudes")
public class SolicitudRestService {

	@EJB
	ISolicitudesEJB solicitudesService;

	@EJB
	IReservasEJB reservasService;

	@GET
	@Path("/find/{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByCedula(@PathParam("cedula") String cedula) {
		try {
			List<SolicitudWithReservasDTO> solicitudesAndReservas = new ArrayList<>();
			List<SolicitudDTO> solicitudes = this.solicitudesService.buscarSolicitud(cedula);

			Consumer<SolicitudDTO> consumer = new Consumer<SolicitudDTO>() {
				@Override
				public void accept(SolicitudDTO t) {
					List<ReservaDTO> reservas = null;

					if(t.getStatus().equals(SolicitudStatus.ASSIGNED) || t.getStatus().equals(SolicitudStatus.DONE)) {
						reservas = SolicitudRestService.this.reservasService.getReservasByCodigo(t.getCodigo());
					}

					solicitudesAndReservas.add(new SolicitudWithReservasDTO(t, reservas));
				}
			};

			solicitudes.forEach(consumer);
			return Response.status(200).entity(solicitudesAndReservas).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@POST
	@Path("/cancelar/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelarSolicitud(@PathParam("codigo") String codigo) {
		try {
			this.solicitudesService.cancelarSolicitud(codigo);

			return Response.status(200).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewSolicitud(SolicitudDTO solicitud){
		try {
			this.solicitudesService.agregarSolicitud(solicitud);
			return Response.status(200).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchByAgendaAndStatus(@QueryParam("idAgenda") Long idAgenda, @QueryParam("status") String status) {
		try {
			ResultSolicitudes solicitudes = this.solicitudesService.getSolicitudesPendientesPorAgendaYEstado(idAgenda, status);
			return Response.status(200).entity(solicitudes).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
}
