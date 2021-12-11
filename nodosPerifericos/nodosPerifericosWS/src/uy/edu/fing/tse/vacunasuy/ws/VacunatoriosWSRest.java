package uy.edu.fing.tse.vacunasuy.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import javax.enterprise.context.RequestScoped;

import uy.edu.fing.tse.vacunasuy.entity.Persona;
import uy.edu.fing.tse.vacunasuy.entity.PuestoVacunacion;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;

import uy.edu.fing.tse.vacunasuy.entity.VacunadorPuestoAsignado;
import uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceLocal;
import uy.edu.fing.tse.vacunasuy.ws.dto.AgendaSesionData;
import uy.edu.fing.tse.vacunasuy.ws.dto.LoteData;
import uy.edu.fing.tse.vacunasuy.ws.dto.PersonaData;
import uy.edu.fing.tse.vacunasuy.ws.dto.PuestoVacunacionData;
import uy.edu.fing.tse.vacunasuy.ws.dto.VacunadorAsignadoData;
import uy.edu.fing.tse.vacunasuy.ws.dto.VacunatorioData;


@RequestScoped
@Path("/vacunatorios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class VacunatoriosWSRest {
@EJB
IVacunatoriosServiceLocal service;
	
	private Integer idVacunatorio=1;
 	public VacunatoriosWSRest() throws NamingException{}
	@GET
	@Path("/vacunadoresPuestos")
	public List<PuestoVacunacionData> obtenerVacunadoresPuestosPorFecha(@HeaderParam("authorization") String token,@QueryParam("fecha")String fecha)
	{
		
	    Date date1=null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    
		List<PuestoVacunacion> lpv= new ArrayList<PuestoVacunacion>();
		List<PuestoVacunacionData> retlpv= new ArrayList<PuestoVacunacionData>();
		if(date1==null)
			lpv=service.obtenerVacunadoresPuesto(idVacunatorio);
			
		lpv= service.obtenerVacunadoresPuesto(idVacunatorio,date1);
		
		for (PuestoVacunacion pv : lpv) {
			PuestoVacunacionData pvd= new PuestoVacunacionData();
			pvd.setPuesto(pv.getCodigo());
			pvd.setFecha(date1);
			pvd.setId(pv.getId());
			if(pv.getVacunadoresAsignados()!=null)
				for (VacunadorPuestoAsignado vpa : pv.getVacunadoresAsignados()) {
					Persona p=vpa.getVacunadorAsignado();
					if(p!=null)
					{
						VacunadorAsignadoData vad= new VacunadorAsignadoData();
						
						vad.setApellido(p.getApellido());
						vad.setCi(p.getCi());
						vad.setNombre(p.getNombre());
						
						
						vad.setRol("Vacunador");
						pvd.getVacunadoresAsignados().add(vad);
					}
					
					
				}
			retlpv.add(pvd);
		}
		
		return retlpv;
		
		
	}
	@GET
	@Path("/agendaSesion")
	public List<AgendaSesionData> obtenerAgendaPorFecha(@HeaderParam("authorization") String token,@QueryParam("fecha")String fecha)
	{
		   Date date1=null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    
			
		List<ReservaVacunatorio> reservasVac= service.obtenerReservasVacunatorio(idVacunatorio,date1);
		List<AgendaSesionData> agendasSession= new ArrayList<AgendaSesionData>();
		
		
		for (ReservaVacunatorio rv : reservasVac) {
			AgendaSesionData as= new AgendaSesionData();
			as.setCodigoSolicitud(rv.getReserva().getSolicitud().getCodigo());
			as.setFecha(date1);
			as.setFechaReserva(rv.getFecha());
			as.setNombreVacuna(rv.getReserva().getEspecificacionDosis().getVacuna().getNombre());
			as.setNroDosis(rv.getNumeroDosis());
			PersonaData p= new PersonaData();
			p.setApellido(rv.getReserva().getSolicitud().getPersona().getApellido());
			p.setNombre(rv.getReserva().getSolicitud().getPersona().getNombre());
			p.setCi(rv.getReserva().getSolicitud().getPersona().getCi());
			as.setPersona(p);
			as.setStatusReserva(rv.getReserva().getStatus());
			as.setStatusSolicitud(rv.getReserva().getSolicitud().getStatus());
			as.setTurnoSolicitud(rv.getReserva().getSolicitud().getTurno());
			VacunatorioData v= new VacunatorioData();
			v.setDepartamento(rv.getVacunatorio().getDepartamento());
			v.setDireccion(rv.getVacunatorio().getDireccion());
			v.setHorarioMatutinoFin(rv.getVacunatorio().getHorarioMatutinoFin());
			v.setHorarioMatutinoInicio(rv.getVacunatorio().getHorarioMatutinoInicio());
			v.setHorarioVespertinoFin(rv.getVacunatorio().getHorarioVespertinoFin());
			v.setHorarioVespertinoInicio(rv.getVacunatorio().getHorarioVespertinoInicio());
			v.setLatitud(rv.getVacunatorio().getLatitud());
			v.setLongitud(rv.getVacunatorio().getLongitud());
			v.setNombre(rv.getVacunatorio().getNombre());
			v.setZona(rv.getVacunatorio().getZona());
			as.setVacunatorio(v);
			as.setZonaSolicitud(rv.getReserva().getSolicitud().getZona());
			agendasSession.add(as);
			
		}
		
		return agendasSession;
		
		
		
	}
	@POST
	@Path("/dosisSuministrada")
	public int reportarDosisSuministradas(@HeaderParam("authorization") String token,LoteData lD)
	{
		if(token==null || token.equals(""))
			 throw new WebApplicationException("Invalid authorization");
	
		Integer idVacunatorio=getVacunatorioIdByToken(token);
	
		if(idVacunatorio==null)
			 throw new WebApplicationException("Invalid authorization");
		 return service.dosisSuministradas(lD.getCodigo(),lD.getFecha(),lD.getCiList(),lD.getEtapaId(),idVacunatorio);
	}
	@POST
	@Path("/setarVacunadoresPuestosFecha")
	public void setarVacunadoresPuestosFecha(@HeaderParam("authorization") String token,List<String> vacunadoresCi,String puestoCodigo, Date fecha)
	{
		if(token==null || token.equals(""))
			 throw new WebApplicationException("Invalid authorization");
	
		Integer idVacunatorio=getVacunatorioIdByToken(token);
		if(idVacunatorio==null)
			 throw new WebApplicationException("Invalid authorization");
		service.setVacunadoresEnPuestoFecha( vacunadoresCi,  puestoCodigo,  fecha, idVacunatorio);
	}
	
	private Integer getVacunatorioIdByToken(String token)
	{
		return service.getVacunatorioIdByToken(token);
	}
	
}
