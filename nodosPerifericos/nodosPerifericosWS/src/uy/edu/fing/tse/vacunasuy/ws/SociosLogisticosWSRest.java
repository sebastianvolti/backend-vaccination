package uy.edu.fing.tse.vacunasuy.ws;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import javax.enterprise.context.RequestScoped;

import uy.edu.fing.tse.vacunasuy.entity.Lote;
import uy.edu.fing.tse.vacunasuy.serviceNP.ISociosLogisticosServiceLocal;
import uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceLocal;
import uy.edu.fing.tse.vacunasuy.ws.dto.LoteData;


@RequestScoped
@Path("/sociosLogisticos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class SociosLogisticosWSRest {
@EJB
ISociosLogisticosServiceLocal service;
	
	
 	public SociosLogisticosWSRest() throws NamingException{}
	
	
 	@GET
	@Path("/pendienteLotes")
	public List<LoteData> pendienteLotes(@HeaderParam("authorization") String token)
	{
		if(token==null || token.equals(""))
			 throw new WebApplicationException("Invalid authorization");
	
		Integer idSl=getSocioLogisticoByToken(token);
		if(idSl==null)
			 throw new WebApplicationException("Invalid authorization");
		List<Lote> lotes=service.getLotesPendiente(idSl);
		List<LoteData> lotesd= new ArrayList<LoteData>();
		for (Lote lote : lotes) {
			LoteData ld= new LoteData();
					ld.setCantidad(lote.getCant());
					ld.setCodigo(lote.getCodigo());
					ld.setEstado(lote.getEstado());
					ld.setVacunatorioDepartamento(lote.getVacunatorio().getDepartamento());
					ld.setVacunatorioDireccion(lote.getVacunatorio().getDireccion());
					ld.setVacunatorioId(lote.getVacunatorio().getId());
					ld.setVacunatorioLatitud(lote.getVacunatorio().getLatitud());
					ld.setVacunatorioLongitud(lote.getVacunatorio().getLongitud());
					ld.setVacunatorioNombre(lote.getVacunatorio().getNombre());
					
					lotesd.add(ld);
					
		}
	return lotesd;
	}

 	@GET
	@Path("/transitoLotes")
	public List<LoteData> transitoLotes(@HeaderParam("authorization") String token)
	{
		if(token==null || token.equals(""))
			 throw new WebApplicationException("Invalid authorization");
	
		Integer idSl=getSocioLogisticoByToken(token);
		if(idSl==null)
			 throw new WebApplicationException("Invalid authorization");
		List<Lote> lotes=service.getLotesTransito(idSl);
		List<LoteData> lotesd= new ArrayList<LoteData>();
		for (Lote lote : lotes) {
			LoteData ld= new LoteData();
					ld.setCantidad(lote.getCant());
					ld.setCodigo(lote.getCodigo());
					ld.setEstado(lote.getEstado());
					ld.setVacunatorioDepartamento(lote.getVacunatorio().getDepartamento());
					ld.setVacunatorioDireccion(lote.getVacunatorio().getDireccion());
					ld.setVacunatorioId(lote.getVacunatorio().getId());
					ld.setVacunatorioLatitud(lote.getVacunatorio().getLatitud());
					ld.setVacunatorioLongitud(lote.getVacunatorio().getLongitud());
					ld.setVacunatorioNombre(lote.getVacunatorio().getNombre());
					
					lotesd.add(ld);
					
		}
	return lotesd;
	}

 	@GET
	@Path("/entregadoLotes")
	public List<LoteData> entregadoLotes(@HeaderParam("authorization") String token)
	{
		if(token==null || token.equals(""))
			 throw new WebApplicationException("Invalid authorization");
	
		Integer idSl=getSocioLogisticoByToken(token);
		if(idSl==null)
			 throw new WebApplicationException("Invalid authorization");
		List<Lote> lotes=service.getLotesEntregado(idSl);
		List<LoteData> lotesd= new ArrayList<LoteData>();
		for (Lote lote : lotes) {
			LoteData ld= new LoteData();
					ld.setCantidad(lote.getCant());
					ld.setCodigo(lote.getCodigo());
					ld.setEstado(lote.getEstado());
					ld.setVacunatorioDepartamento(lote.getVacunatorio().getDepartamento());
					ld.setVacunatorioDireccion(lote.getVacunatorio().getDireccion());
					ld.setVacunatorioId(lote.getVacunatorio().getId());
					ld.setVacunatorioLatitud(lote.getVacunatorio().getLatitud());
					ld.setVacunatorioLongitud(lote.getVacunatorio().getLongitud());
					ld.setVacunatorioNombre(lote.getVacunatorio().getNombre());
					
					lotesd.add(ld);
					
		}
	return lotesd;
	}
	@POST
	@Path("/entregaLote")
	public Map<String,Integer> entregaLote(@HeaderParam("authorization") String token,List<LoteData> lotes)
	{
		if(token==null || token.equals(""))
			 throw new WebApplicationException("Invalid authorization");
	
		Integer idSl=getSocioLogisticoByToken(token);
		if(idSl==null)
			 throw new WebApplicationException("Invalid authorization");
	
		Map<String, Integer> ret= new HashMap<String,Integer>();
		for (LoteData loteData : lotes) {
		
			
			ret.put(loteData.getCodigo(), service.recibirLote(loteData.getCodigo(), loteData.getCantidad(), idSl));
		}
		 
		return ret;
	}
	
	@POST
	@Path("/transporteLote")
	public Map<String,Integer> transporteLote(@HeaderParam("authorization") String token,List<LoteData> lotes)
	{
		if(token==null || token.equals(""))
			 throw new WebApplicationException("Invalid authorization");
	
		Integer idSl=getSocioLogisticoByToken(token);
		if(idSl==null)
			 throw new WebApplicationException("Invalid authorization");
	
		Map<String, Integer> ret= new HashMap<String,Integer>();
		for (LoteData loteData : lotes) {
		
			
			ret.put(loteData.getCodigo(), service.transporteLote(loteData.getCodigo(), loteData.getCantidad(), idSl));
		}
		 
		return ret;
	}
	

	

	private Integer getSocioLogisticoByToken(String token)
	{
		return service.getSocioLogisticoIdByToken(token);
	}
	
}
