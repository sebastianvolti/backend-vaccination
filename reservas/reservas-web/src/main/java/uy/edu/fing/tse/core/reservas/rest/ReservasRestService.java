package uy.edu.fing.tse.core.reservas.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import uy.edu.fing.tse.core.reservas.dto.DateHistorgramDTO;
import uy.edu.fing.tse.core.reservas.dto.ReservaDTO;
import uy.edu.fing.tse.core.reservas.dto.ResultReservas;
import uy.edu.fing.tse.core.reservas.multipart.MultipartBody;
import uy.edu.fing.tse.core.reservas.service.IReservasEJB;

@Path("/reservas")
public class ReservasRestService {

	@EJB
	IReservasEJB reservasEJB;

	@POST
	@Path("/cupos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response liberarCupos(@QueryParam("idAgenda") Long idAgenda, @QueryParam("cant") Long cantCupos) {
		try {
			int cuposLiberados = this.reservasEJB.liberarCupos(idAgenda, cantCupos);
			return Response.status(200).entity(String.format("{\"cupos\": %d}", cuposLiberados)).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@GET
	@Path("/codigo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response liberarCupos(@QueryParam("codigo") String codigo) {
		try {
			List<ReservaDTO> reservas = this.reservasEJB.getReservasByCodigo(codigo);
			return Response.status(200).entity(reservas).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@GET
	@Path("/agenda")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservasByAgenda(@QueryParam("idAgenda") Long idAgenda) {
		try {
			ResultReservas reservas = this.reservasEJB.getReservasByAgenda(idAgenda);

			return Response.status(200).entity(reservas).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@GET
	@Path("/histogram/vacunados")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDateHistogramByDay() {
		try {
			DateHistorgramDTO histogram = this.reservasEJB.getDateHistogram();
			return Response.status(200).entity(histogram).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@GET
	@Path("/vacunatorios/agenda")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDateHistogramByDayAndVacunatorio(@QueryParam("idVacunatorio") Long idVacunatorio) {
		try {
			DateHistorgramDTO histogram = this.reservasEJB.getDateHistogramVacunatorio(idVacunatorio);
			return Response.status(200).entity(histogram).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@GET
	@Path("/vacunatorios/agenda/dia")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getVacunatorioAgendaByDay(@QueryParam("idVacunatorio") Long idVacunatorio, @QueryParam("day") String day) {
		try {
			XSSFWorkbook workbook = this.reservasEJB.buildDownloadEmptyExcel(idVacunatorio, day);
			String fileName = day + ".xlsx";

			StreamingOutput stream = new StreamingOutput() {
				@Override
				public void write(OutputStream output) throws IOException, WebApplicationException {
					workbook.write(output);
					workbook.close();
				}
			};


			ResponseBuilder response = Response.ok(stream);
			response.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"" );

			return response.build();


		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@POST
	@Path("/vacunatorios/agenda/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@QueryParam("idVacunatorio") Long idVacunatorio, @MultipartForm MultipartBody input) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(input.file);

			this.reservasEJB.parseAndExecutoUploadExcel(workbook, idVacunatorio);

			workbook.close();
			return Response.status(200).build();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}
}
