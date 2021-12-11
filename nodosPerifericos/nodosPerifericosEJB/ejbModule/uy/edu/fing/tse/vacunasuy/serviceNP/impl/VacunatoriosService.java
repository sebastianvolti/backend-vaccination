package uy.edu.fing.tse.vacunasuy.serviceNP.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import uy.edu.fing.tse.vacunasuy.dtos.EtapaDTO;
import uy.edu.fing.tse.vacunasuy.dtos.HorasDTO;
import uy.edu.fing.tse.vacunasuy.dtos.PlanDTO;
import uy.edu.fing.tse.vacunasuy.dtos.PuestoAsignadoDTO;
import uy.edu.fing.tse.vacunasuy.entity.DiaHora;
import uy.edu.fing.tse.vacunasuy.entity.DiaHoraLugar;
import uy.edu.fing.tse.vacunasuy.entity.Enfermedad;
import uy.edu.fing.tse.vacunasuy.entity.Etapa;
import uy.edu.fing.tse.vacunasuy.entity.Hora;
import uy.edu.fing.tse.vacunasuy.entity.Lote;
import uy.edu.fing.tse.vacunasuy.entity.LoteDosis;
import uy.edu.fing.tse.vacunasuy.entity.Persona;
import uy.edu.fing.tse.vacunasuy.entity.Plan;
import uy.edu.fing.tse.vacunasuy.entity.PuestoVacunacion;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.Rol;
import uy.edu.fing.tse.vacunasuy.entity.SocioLogistico;
import uy.edu.fing.tse.vacunasuy.entity.VacunadorPuestoAsignado;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;
import uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceLocal;
import uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceRemote;



@Stateless
@Remote (IVacunatoriosServiceRemote.class)
@Local (IVacunatoriosServiceLocal.class)
public class VacunatoriosService implements IVacunatoriosServiceRemote,IVacunatoriosServiceLocal {
	@PersistenceContext(unitName = "vacunasuyJPA")
	private EntityManager em;
	
	@Override
	public List<PuestoVacunacion> obtenerVacunadoresPuesto(Integer idVacunatorio,Date fecha) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("PuestoVacunacion.findAllByVacByDate");
		query.setParameter("fecha", fecha);
		query.setParameter("idVacunatorio", idVacunatorio);
		List<PuestoVacunacion> lst=query.getResultList();
		if (lst!=null)
		for (PuestoVacunacion l : lst) {
			if(l.getVacunadoresAsignados()!=null)
			{
				for (VacunadorPuestoAsignado  vpa: l.getVacunadoresAsignados()) {
					if(vpa!=null)
						if(vpa.getVacunadorAsignado()!=null)
							vpa.getVacunadorAsignado().getId();
				}
			}
							
		}
		return lst;
	
	}
	
	@Override
	public List<PuestoVacunacion> obtenerVacunadoresPuesto(Integer idVacunatorio) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("PuestoVacunacion.findAllByVac");
		query.setParameter("idVacunatorio", idVacunatorio);
		List<PuestoVacunacion> lst=query.getResultList();
		if (lst!=null)
		for (PuestoVacunacion l : lst) {
			if(l.getVacunadoresAsignados()!=null)
			{
				for (VacunadorPuestoAsignado  vpa: l.getVacunadoresAsignados()) {
					if(vpa!=null)
						if(vpa.getVacunadorAsignado()!=null)
							vpa.getVacunadorAsignado().getId();
				}
			}
			
				
			
			
		}
		return lst;
	
	}

	@Override
	public int dosisSuministradas(String codigoLote,  Date fecha,List<String> ciList,Integer etapaId,  Integer vacunatorioId) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("Lote.findByCodigoByVacunatorioId");
		query.setParameter("codigo", codigoLote);
		query.setParameter("vacunatorioId", vacunatorioId);
		
		try
		{
			Lote l= (Lote) query.getSingleResult();
			Etapa e= em.find(Etapa.class, etapaId);
			
			LoteDosis ld= new LoteDosis(codigoLote,ciList.size(), fecha,l,"suministrada",e);
			
			for (String ci : ciList) {
				Query query2 = em.createNamedQuery("Persona.findAllByCi");
					query2.setParameter("ci", ci);
					
					List<Persona> lst=query2.getResultList();
					if (lst!=null && lst.size()>0)
					{
						Persona p=lst.get(0);
						if(ld.getVacunados()==null)
							ld.setVacunados(new ArrayList<Persona>());
						
						if(!ld.getVacunados().contains(p))
						{
							ld.getVacunados().add(p);
							if(p.getDosisRecibidas()==null)
								p.setDosisRecibidas(new ArrayList<LoteDosis>());
							p.getDosisRecibidas().add(ld);
									
						}
					}
			}
			
			
			l.getDosis().add(ld);
			em.merge(l);
			
			return 0;
			
		}
		catch(NoResultException e)
		{
			return -1;
		}
	
		
		
	}

	@Override
	public List<ReservaVacunatorio> obtenerReservasVacunatorio(Integer idVacunatorio, Date fecha) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("ReservaVacunatorio.findAllByVacByDate");
		query.setParameter("idVacunatorio", idVacunatorio);
		query.setParameter("fecha", fecha);
		
		List<ReservaVacunatorio> lst=query.getResultList();
		if (lst!=null)
		for (ReservaVacunatorio l : lst) {
			if(l.getReserva()!=null)
			{
				if(l.getVacunatorio()!=null)
				{
					l.getVacunatorio().getId();
					
					if(l.getVacunatorio().getPuestosVacunacion()!=null)
					{
						
						for (PuestoVacunacion pv : l.getVacunatorio().getPuestosVacunacion()) {
						
							pv.getId();
							if(pv.getVacunadoresAsignados()!=null)
								for (VacunadorPuestoAsignado vpa : pv.getVacunadoresAsignados()) {
								vpa.getId();
								}
						}
					}
						
				}
				l.getReserva().getId();
				if(l.getReserva().getEspecificacionDosis()!=null)
				{
					l.getReserva().getEspecificacionDosis().getId();
					if(l.getReserva().getEspecificacionDosis().getVacuna()!=null)
					l.getReserva().getEspecificacionDosis().getVacuna().getId();
				}
				if(l.getReserva().getSolicitud()!=null)
				{
				
					l.getReserva().getSolicitud().getId();
					if(l.getReserva().getSolicitud().getPersona()!=null)
						l.getReserva().getSolicitud().getPersona().getId();
					if(l.getReserva().getSolicitud().getPersona().getPuestosAsignados()!=null)
						for (VacunadorPuestoAsignado vpa : l.getReserva().getSolicitud().getPersona().getPuestosAsignados()) {
							vpa.getId();
						}
				}
			}
		}
		return lst;
	}

	@Override
	public List<Vacunatorio> getVacunatoriosAll() {
		// TODO Auto-generated method stub
		List<Vacunatorio> objList=null;
	Query query= em.createNamedQuery("Vacunatorio.findAll");
	objList= query.getResultList();
	for (Vacunatorio enf : objList) {
		if(enf.getPuestosVacunacion()!=null)
		enf.getPuestosVacunacion().size();
	}
	
		return objList;
	}

	@Override
	public Vacunatorio getVacunatorioLazyById(Integer id) {
		// TODO Auto-generated method stub
		Vacunatorio v=em.find(Vacunatorio.class, id);
		if(v!=null)
		{
			if(v.getPuestosVacunacion()!=null)
				for (PuestoVacunacion p : v.getPuestosVacunacion()) 
					if(p.getVacunadoresAsignados()!=null)
						for (VacunadorPuestoAsignado va : p.getVacunadoresAsignados())
							if(va.getVacunadorAsignado()!=null)
								va.getVacunadorAsignado().getCi();
		
			if(v.getReservas()!=null)
				for (ReservaVacunatorio r : v.getReservas())
					if(r.getReserva()!=null)
						r.getReserva().getStatus();
			if(v.getLotes()!=null)
				for (Lote l : v.getLotes())
					l.getCant();
						
		}
			return v;
	}

	@Override
	public Boolean eliminarVacunatorio(Integer id) {
		// TODO Auto-generated method stub
		Vacunatorio v=em.find(Vacunatorio.class,id);
		try
		{
			em.remove(v);
			return true;
		}
		catch (PersistenceException e) {
				return false;
		}
	}

	@Override
	public Vacunatorio guardarVacunatorio(Vacunatorio vacunatorioSeleccionado) {
		// TODO Auto-generated method stub
		return em.merge(vacunatorioSeleccionado);
	}

	@Override
	public List<PuestoAsignadoDTO> obtener5PuestosVacunador(String cedula, Date fecha) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("VacunadorPuestoAsignado.findAllByCiByFecha");
		query.setParameter("ci", cedula);
		query.setParameter("fecha", fecha);
		query.setMaxResults(5);
		List<PuestoAsignadoDTO> lres= new ArrayList<PuestoAsignadoDTO>();
		List<VacunadorPuestoAsignado> lst=query.getResultList();
		if (lst!=null)
		for (VacunadorPuestoAsignado l : lst) {
			PuestoAsignadoDTO p= obtenerPuestoAsignadoDTO(lres,l.getId());
			if(p==null)
				{
					p= new PuestoAsignadoDTO();
					p.setCodigoPuesto(l.getPuestoTrabajoAsignado().getCodigo());
					p.setDepartamento(l.getPuestoTrabajoAsignado().getVacunatorio().getDepartamento());
					p.setDireccion(l.getPuestoTrabajoAsignado().getVacunatorio().getDireccion());
					p.setFecha(l.getFecha());
					p.setHorarioMatutinoFin(l.getPuestoTrabajoAsignado().getVacunatorio().getHorarioMatutinoFin());
					p.setHorarioMatutinoInicio(l.getPuestoTrabajoAsignado().getVacunatorio().getHorarioMatutinoInicio());
					p.setHorarioVespertinoFin(l.getPuestoTrabajoAsignado().getVacunatorio().getHorarioVespertinoFin());
					p.setHorarioVespertinoInicio(l.getPuestoTrabajoAsignado().getVacunatorio().getHorarioVespertinoInicio());
					p.setIdPuesto(l.getPuestoTrabajoAsignado().getId());
					p.setIdVacunatorio(l.getPuestoTrabajoAsignado().getVacunatorio().getId());
					p.setLatitud(l.getPuestoTrabajoAsignado().getVacunatorio().getLatitud());
					p.setLongitud(l.getPuestoTrabajoAsignado().getVacunatorio().getLongitud());
					p.setNombre(l.getPuestoTrabajoAsignado().getVacunatorio().getNombre());
					p.setZona(l.getPuestoTrabajoAsignado().getVacunatorio().getZona());
					lres.add(p);
				}
		
			Query query2 = em.createNamedQuery("DiaHoraLugar.findAllByVacunatorioIdByFecha");
			query2.setParameter("vacunatorioId", p.getIdVacunatorio());
			query2.setParameter("fecha", fecha);
			List<String> horas= new ArrayList<String>();
			List<DiaHoraLugar> lst2=query2.getResultList();
			
			if(lst2!=null)
				for (DiaHoraLugar dhl : lst2) {
					if(dhl.getHoras()!=null)
						for (Hora h : dhl.getHoras()) 
							if(!horas.contains(h.getNombre()))
								horas.add(h.getNombre());
					if(p.getPlanes()==null)
						p.setPlanes(new ArrayList<PlanDTO>());
					Plan plan=dhl.getAgenda().getEtapa().getPlan();
					if(p.getPlanes()==null)
						p.setPlanes(new ArrayList<PlanDTO>());
					
					PlanDTO planD= obtenerPlanDTO(p.getPlanes(),plan.getId());
					if(planD==null)
						{
							planD= new PlanDTO();
							planD.setId(plan.getId());
							planD.setPlanNombre(plan.getNombre());
							p.getPlanes().add(planD);
						}
					
					Etapa etapa=dhl.getAgenda().getEtapa();
					if(planD.getEtapas()==null)
						planD.setEtapas(new ArrayList<EtapaDTO>());
					
					EtapaDTO etapaD= obtenerEtapaDTO(planD.getEtapas(),etapa.getId());
					if(etapaD==null)
						{
							etapaD= new EtapaDTO();
							etapaD.setId(etapa.getId());
							etapaD.setEtapaNombre(etapa.getNombre());
							etapaD.setVacuna(etapa.getVacuna().getNombre());
							planD.getEtapas().add(etapaD);
						}
					
					if(etapaD.getEnfermedades()==null)
						etapaD.setEnfermedades(new ArrayList<String>());
					
					List<String> enfDTos=etapaD.getEnfermedades();
					List<Enfermedad> le=etapa.getVacuna().getEnfermedades();
					if(le!=null)
						for (Enfermedad enf: le) 
							if(!enfDTos.contains(enf.getNombre()))
								enfDTos.add(enf.getNombre());
						
					if(etapaD.getHoras()==null)
						etapaD.setHoras(new ArrayList<HorasDTO>());
					
					List<HorasDTO> hsdtos=etapaD.getHoras();
					List<Hora> lh=dhl.getHoras();
					if(lh!=null)
						for (Hora hs: lh) 
						{
							if(!existeHoraDTO(hsdtos,hs.getNombre()))
							{
								HorasDTO hdto= new HorasDTO();
								hdto.setHora(hs.getNombre());
								hsdtos.add(hdto);
							}
						}
					
					
				}
				
			
		}	
		return lres;
	}

	
	private PlanDTO obtenerPlanDTO(List<PlanDTO> planes, Integer id) {
		// TODO Auto-generated method stub
		if(planes!=null)
			for (PlanDTO p : planes) {
				if(p.getId()!=null && p.getId().equals(id))
					return p;
			}
			return null;
			
	}

	private EtapaDTO obtenerEtapaDTO(List<EtapaDTO> etapas, Integer id) {
		// TODO Auto-generated method stub
		if(etapas!=null)
			for (EtapaDTO p : etapas) {
				if(p.getId()!=null && p.getId().equals(id))
					return p;
			}
			return null;
			
	}

	private boolean existeHoraDTO(List<HorasDTO> l, String nombre) {
		// TODO Auto-generated method stub
		if(l!=null)
			for (HorasDTO p : l) {
				if(p.getHora()!=null && p.getHora().equals(nombre))
					return true;
			}
			return false;
	}

	private PuestoAsignadoDTO obtenerPuestoAsignadoDTO(List<PuestoAsignadoDTO> lres, Integer id) {
		// TODO Auto-generated method stub
		if(lres!=null)
		for (PuestoAsignadoDTO p : lres) {
			if(p.getIdPuesto()!=null && p.getIdPuesto().equals(id))
				return p;
		}
		return null;
		
					
	}

	@Override
	public void setVacunadoresEnPuestoFecha(List<String> vacunadoresCi, String puestoCodigo,Date fecha, Integer vacunatorioId) {
		// TODO Auto-generated method stub
		Rol rolVac=getRolVacunador();
		Vacunatorio v=em.find(Vacunatorio.class, vacunatorioId);
		if(v.getPuestosVacunacion()==null)
			v.setPuestosVacunacion(new ArrayList<PuestoVacunacion>());
		List<PuestoVacunacion> lst=v.getPuestosVacunacion();
		PuestoVacunacion pv=null;
		if(lst!=null && lst.size()>0)
		{
			for (PuestoVacunacion pvo : lst) {
				if(pvo.getCodigo().equals(puestoCodigo))
					pv=pvo;
			}
		}
		else
		{
			pv= new PuestoVacunacion();
			pv.setCodigo(puestoCodigo);
			pv.setVacunatorio(v);
			pv.setVacunadoresAsignados(new ArrayList<VacunadorPuestoAsignado>());
			v.getPuestosVacunacion().add(pv);
			
		}
		List<VacunadorPuestoAsignado> lvpanew=new ArrayList<VacunadorPuestoAsignado>();
		
		if(pv.getVacunadoresAsignados()!=null) {
			for (VacunadorPuestoAsignado vpa : pv.getVacunadoresAsignados()) {
				if(!vpa.getFecha().equals(fecha))
				{
					lvpanew.add(vpa);
				}
				else
					vpa.setPuestoTrabajoAsignado(null);
			}
		}
		
		
		for (String vci : vacunadoresCi) {
			VacunadorPuestoAsignado pva= new VacunadorPuestoAsignado();
			Persona vac=getLazyPersonaByCi(vci);
			if(vac==null)
			{
				vac=new Persona();
				vac.setCi(vci);
				
			}
			if(!vac.getRoles().contains(rolVac))
			{
				vac.getRoles().add(rolVac);
				rolVac.getPersonas().add(vac);
			}
			vac=em.merge(vac);
			
					
			pva.setFecha(fecha);
			pva.setPuestoTrabajoAsignado(pv);
			pva.setVacunadorAsignado(vac);
			lvpanew.add(pva);
		}
		pv.setVacunadoresAsignados(lvpanew);
		em.merge(v);
		
	}


	private Persona getLazyPersonaByCi(String ci)
	{
		Query query= em.createNamedQuery("Persona.findAllByCi");
		query.setParameter("ci",ci);
		query.setMaxResults(1);
		Persona p=null;
		try
		{
			List<Persona> pl=query.getResultList();
			if(pl!=null && pl.size()>0)
			{
				p= pl.get(0); 
			if(p.getRoles()!=null)
				for (Rol rol : p.getRoles()) {
					rol.getNombre();
				}
			}
			
		}
		catch ( Exception e) {
			return null;
		}
		
		return  p;
	}
	private List<Rol> getRolesAll() {
		// TODO Auto-generated method stub
		List<Rol> objList=null;
		Query query= em.createNamedQuery("Rol.findAll");
		objList= query.getResultList();
		if(objList!=null)
			for (Rol rol : objList) {
				if(rol.getPersonas()!=null)
					rol.getPersonas().size();
			}
		
			return objList;
	}
	private Rol getRolVacunador() {
		// TODO Auto-generated method stub
		List<Rol> objList=null;
		Query query= em.createNamedQuery("Rol.findAll");
		objList= query.getResultList();
		if(objList!=null)
			for (Rol rol : objList) {
				if(rol.getNombre().equals("Vacunador"))
					return rol;
			}
		
			return null;
	}

	@Override
	public Integer getVacunatorioIdByToken(String token) {
		// TODO Auto-generated method stub

		Query query= em.createNamedQuery("Vacunatorio.findByToken");
		query.setParameter("token",token);
		
		Vacunatorio v=null;
		try
		{
			v=(Vacunatorio) query.getSingleResult();
			return v.getId();
		}
		catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
}
