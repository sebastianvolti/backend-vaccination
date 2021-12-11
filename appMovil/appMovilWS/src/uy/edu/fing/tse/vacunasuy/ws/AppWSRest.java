package uy.edu.fing.tse.vacunasuy.ws;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import uy.edu.fing.tse.vacunasuy.entity.Enfermedad;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;
import uy.edu.fing.tse.vacunasuy.service.IAppServiceLocal;

import uy.edu.fing.tse.vacunasuy.ws.dto.CertificadoData;
import uy.edu.fing.tse.vacunasuy.ws.dto.DosisData;
import uy.edu.fing.tse.vacunasuy.ws.dto.VacunatorioData;

import javax.enterprise.context.RequestScoped;



@RequestScoped
@Path("/app")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
public class AppWSRest {

	@EJB
	IAppServiceLocal service;
 	public AppWSRest() throws NamingException{}
	
 	

 	@GET
	@Path("/vacunatorios")
	public List<VacunatorioData> obtenerVacunatorios()
	{
		List<Vacunatorio> vacunatorios= service.obtenerVacunatorios();
		List<VacunatorioData> vsd= new ArrayList<VacunatorioData>();
		for (Vacunatorio v : vacunatorios) {
			VacunatorioData vd= new VacunatorioData();
			vd.setDepartamento(v.getDepartamento());
			vd.setDireccion(v.getDireccion());
			vd.setHorarioMatutinoFin(v.getHorarioMatutinoFin());
			vd.setHorarioMatutinoInicio(v.getHorarioMatutinoInicio());
			vd.setHorarioVespertinoFin(v.getHorarioVespertinoFin());
			vd.setHorarioVespertinoInicio(v.getHorarioVespertinoInicio());
			vd.setLatitud(v.getLatitud());
			vd.setLongitud(v.getLongitud());
			vd.setNombre(v.getNombre());
			vd.setZona(v.getZona());
			vsd.add(vd);	
		}
		return vsd;
	}
 	@GET
	@Path("/certificados")
	public List<CertificadoData> obtenerCertificados(@QueryParam("ci")String ci)
	{
 			List<CertificadoData> certificados= new ArrayList<CertificadoData>();
 			List<ReservaVacunatorio> reservasVac= service.obtenerReservasCompletadasVacunatorio(ci);
		
 			
		
		for (ReservaVacunatorio rv : reservasVac) {
			Boolean nuevo=false;
			CertificadoData c=certificados.stream()
					  .filter(cert -> rv.getReserva().getEspecificacionDosis().getVacuna().getId()==cert.getId())
					  .findAny()
					  .orElse(null);
			
			if(c==null)
			{
				 nuevo=true;
				 c= new CertificadoData();
				c.setId(rv.getReserva().getEspecificacionDosis().getVacuna().getId());
				c.setEstado("Completado");
				c.setNombreLaboratorio(rv.getReserva().getEspecificacionDosis().getVacuna().getLaboratoriosString());
				c.setNombreVacuna(rv.getReserva().getEspecificacionDosis().getVacuna().getNombre());
				c.setNombrePlan(rv.getReserva().getSolicitud().getAgenda().getEtapa().getPlan().getNombre());
				List<Enfermedad> le= rv.getReserva().getEspecificacionDosis().getVacuna().getEnfermedades();
				List<String> les= new ArrayList<String>();
				for (Enfermedad enf : le) 
					if(!les.contains(enf.getNombre()))
						les.add(enf.getNombre());
				c.setEnfermedades(les);
			}
		rv.getReserva().getEspecificacionDosis().getVacuna().getDiasDeInmunizacion();
			
			DosisData ds= new DosisData();
			ds.setFecha(rv.getFecha());
			ds.setNumDosis(rv.getNumeroDosis());
			ds.setVacunatorioNombre(rv.getVacunatorio().getNombre());
			c.getDosis().add(ds);
			c.setInmunizacionDesde(rv.getFecha());
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(rv.getFecha());
			   calendar.add(Calendar.DATE, rv.getReserva().getEspecificacionDosis().getVacuna().getDiasDeInmunizacion());
			c.setInmunizacionHasta(calendar.getTime());
			
			if(nuevo)
			certificados.add(c);
			
			
		}
		
		return certificados;
		
	}
	
}
