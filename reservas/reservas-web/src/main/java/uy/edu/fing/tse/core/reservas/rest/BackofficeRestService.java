package uy.edu.fing.tse.core.reservas.rest;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Strings;

import uy.edu.fing.tse.core.reservas.rest.entities.EnfermedadEntity;
import uy.edu.fing.tse.core.reservas.rest.entities.VacunatorioEntity;
import uy.edu.fing.tse.vacunasuy.entity.Enfermedad;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;
import uy.edu.fing.tse.vacunasuy.serviceAP.IEnfermedadesServiceRemote;
import uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceRemote;

@Path("/backoffice")
public class BackofficeRestService {

	@GET
	@Path("/vacunatorios")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVacunatorios(@QueryParam("departamento") String departamento) {
		try {
			List<Vacunatorio> vacunatorios = this.getVacunatoriosRemote().getVacunatoriosAll();

			List<VacunatorioEntity> vacunatoriosEntity = vacunatorios
					.stream()
					.map(v -> new VacunatorioEntity(v.getId(), v.getNombre(), v.getDepartamento()))
					.filter(v -> Strings.isNullOrEmpty(departamento) || v.getDepartamento().replace(" ", "").equalsIgnoreCase(departamento))
					.collect(Collectors.toList())
					;


			return Response.status(200).entity(vacunatoriosEntity).build();
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

	@GET
	@Path("/enfermedades")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEnfermedades() {
		try {
			List<Enfermedad> enfermedades = this.getEnfermedadesRemote().getEnfermedadesAll();

			List<EnfermedadEntity> enfermedadesEntity = enfermedades
					.stream()
					.map(e -> new EnfermedadEntity(e.getId(), e.getNombre())).collect(Collectors.toList());

			return Response.status(200).entity(enfermedadesEntity).build();
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}


	private IEnfermedadesServiceRemote getEnfermedadesRemote() {
		Properties props = new Properties();

		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");   //$NON-NLS-1$
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");   //$NON-NLS-1$
		props.put(Context.PROVIDER_URL,"http-remoting://core-07.web.elasticloud.uy:80");
		props.put(Context.SECURITY_PRINCIPAL, "backoffice"); //$NON-NLS-1$
		props.put(Context.SECURITY_CREDENTIALS, "backoffice"); //$NON-NLS-1$
		Context ctx;
		try {
			ctx = new InitialContext(props);

			IEnfermedadesServiceRemote service =  (IEnfermedadesServiceRemote)ctx.lookup("ejb:agendaPlanes/agendaPlanesEJB/EnfermedadesService!uy.edu.fing.tse.vacunasuy.serviceAP.IEnfermedadesServiceRemote"); //$NON-NLS-1$

			return service;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

	private IVacunatoriosServiceRemote getVacunatoriosRemote() {
		Properties props = new Properties();

		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");   //$NON-NLS-1$
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");   //$NON-NLS-1$
		props.put(Context.PROVIDER_URL,"http-remoting://core-07.web.elasticloud.uy:80");
		props.put(Context.SECURITY_PRINCIPAL, "backoffice"); //$NON-NLS-1$
		props.put(Context.SECURITY_CREDENTIALS, "backoffice"); //$NON-NLS-1$

		Context ctx;
		try {
			ctx = new InitialContext(props);

			IVacunatoriosServiceRemote service =  (IVacunatoriosServiceRemote)ctx.lookup("ejb:nodosPerifericos/nodosPerifericosEJB/VacunatoriosService!uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceRemote"); //$NON-NLS-1$
			return service;

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


}
