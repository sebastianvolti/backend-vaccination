package uy.edu.fing.tse.vacunasuy.implAP;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

import uy.edu.fing.tse.vacunasuy.dtos.DisponibilidadDTO;
import uy.edu.fing.tse.vacunasuy.dtos.ReporteDTO;
import uy.edu.fing.tse.vacunasuy.entity.Agenda;
import uy.edu.fing.tse.vacunasuy.entity.DiaHora;
import uy.edu.fing.tse.vacunasuy.entity.Enfermedad;
import uy.edu.fing.tse.vacunasuy.entity.EspecificacionVacuna;
import uy.edu.fing.tse.vacunasuy.entity.Etapa;

import uy.edu.fing.tse.vacunasuy.entity.Laboratorio;
import uy.edu.fing.tse.vacunasuy.entity.Persona;
import uy.edu.fing.tse.vacunasuy.entity.Plan;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;
import uy.edu.fing.tse.vacunasuy.serviceAP.IPlanesServiceRemote;

@Stateless
@Remote (IPlanesServiceRemote.class)
public class PlanesService implements IPlanesServiceRemote {

	@PersistenceContext(unitName = "vacunasuyJPA")
	private EntityManager em;
	
	

		
	@Override
	public List<Plan> getPlanesAll() {
		// TODO Auto-generated method stub
		List<Plan> planes=null;
	Query query= em.createNamedQuery("Plan.findAll");
	planes= query.getResultList();
	for (Plan plan : planes) {
		if(plan.getEtapas()!=null)
		plan.getEtapas().size();
		if(plan.getEnfermedades()!=null)
		plan.getEnfermedades().size();
	}
	
		return planes;
	}
	@Override
	public Plan guardarPlan(Plan planSeleccionado) {
		// TODO Auto-generated method stub
		Plan pn=em.merge(planSeleccionado);
		return getPlanLazyById(pn.getId());
	}
	@Override
	public Plan getPlanLazyById(Integer planId) {
		// TODO Auto-generated method stub
		Plan plan=em.find(Plan.class,planId);
			if(plan.getEtapas()!=null)
				for (Etapa e : plan.getEtapas())
				{
					if(e.getVacuna()!=null)
							if(e.getVacuna().getLaboratorios()!=null)
								for (Laboratorio l : e.getVacuna().getLaboratorios())
										l.getNombre();
					if(e.getPublicoObjetivo()!=null)
					e.getPublicoObjetivo().size();
				}
			if(plan.getEnfermedades()!=null)
				for (Enfermedad enf : plan.getEnfermedades()) 
					if(enf.getVacunas()!=null)
						for (EspecificacionVacuna v : enf.getVacunas())
							if(v.getLaboratorios()!=null)
							{
								for (Laboratorio l : v.getLaboratorios())
										l.getNombre();
								v.getLaboratoriosString();
							}
				
			
			return plan;
	}
	@Override
	public void eliminarPlan(Integer id) {
		// TODO Auto-generated method stub
		Plan pl=em.find(Plan.class, id);
		if(pl!=null)
			em.remove(pl);
	}
	@Override
	public List<Agenda> getAgendasByPlanId(Integer planId) {
		// TODO Auto-generated method stub
		List<Agenda> objList=new ArrayList<Agenda>();
		Query query= em.createNamedQuery("Agenda.findAllByPlanId");
		query.setParameter("planId", planId);
		objList= query.getResultList();
		if(objList!=null)
		for (Agenda obj : objList) {
			obj.getEtapa().getNombre();
			if(obj.getVacunatorio()!=null)
				obj.getVacunatorio().getNombre();
		}
		
		
			return objList;
	}
	@Override
	public Agenda getAgendaLazyById(Integer id) {
		// TODO Auto-generated method stub
		Agenda a=em.find(Agenda.class, id);
		if(a.getVacunatorio()!=null)
			a.getVacunatorio().getNombre();
		if(a.getEtapa()!=null)
			a.getEtapa().getNombre();
		if(a.getDiasHoraLugar()!=null)
		{
			if(a.getDiasHoraLugar().getDias()!=null)
				a.getDiasHoraLugar().getDias().size();
			if(a.getDiasHoraLugar().getHoras()!=null)
				a.getDiasHoraLugar().getHoras().size();
			if(a.getDiasHoraLugar().getDiaHoraEspecifico()!=null)
				a.getDiasHoraLugar().getDiaHoraEspecifico().size();
			if(a.getDiasHoraLugar().getVacunatorio()!=null)
				a.getDiasHoraLugar().getVacunatorio().getNombre();
			if(a.getExcepciones()!=null)
				a.getExcepciones().size();
			
		}
		return a;
	
	}
	@Override
	public Agenda guardarAgenda(Agenda agendaSeleccionada) {
		// TODO Auto-generated method stub
		Agenda pn=em.merge(agendaSeleccionada);
		return getAgendaLazyById(pn.getId());
	}
	@Override
	public void eliminarAgenda(Integer id) {
		// // TODO Auto-generated method stub
		Agenda pl=em.find(Agenda.class, id);
		if(pl!=null)
			em.remove(pl);
		
	}
	@Override
	public List<DisponibilidadDTO> obtenerDisponibilidad(String ci, Integer enfermedadId, Integer vacunatorioId,
			Boolean horarioMatutino, Integer nroDosisDesde) {
		Enfermedad e=em.find(Enfermedad.class, enfermedadId);
		if(e==null)
			return null;//enfermedad no existe
		Vacunatorio v=em.find(Vacunatorio.class, vacunatorioId);
		if(v==null)
			return null;//vacunatorio no existe
		Persona p=null;
			Query query= em.createNamedQuery("Persona.findCiudadanoByCi");
			query.setParameter("ci",ci);
			try
			{
				 p= (Persona) query.getSingleResult();
				
			}
			catch ( Exception e2) {
				return null; //no existe el ciudadano
			}
			
		
			LocalDate today = LocalDate.now();                          //Today's date
		
		Query query2= em.createNamedQuery("Plan.findAllByEnfermedadId");
		query2.setParameter("enfermedadId", e.getId());
	
		List<DiaHora> lDiaHoraAsignar= new ArrayList<DiaHora>();
		List<DisponibilidadDTO> disList=new ArrayList<DisponibilidadDTO>();
		List<Plan> planes= query2.getResultList();
		if(planes!=null)
		for (Plan plan : planes) {
			if(plan.getEtapas()!=null)
				for (Etapa etapa : plan.getEtapas()) {
					if(etapa.getFin().compareTo(new Date())>=0 && etapa.getCupoRestante()>0)
					{
						LocalDate birthday = p.getFechaNacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						 
						Period period = Period.between(birthday, today);
					
					
						if(etapa.getEdadMaxima()==null || (etapa.getEdadMaxima()!=null &&  etapa.getEdadMaxima()>=period.getYears())
							&&(etapa.getEdadMinima()==null || (etapa.getEdadMinima()!=null &&  etapa.getEdadMaxima()<=period.getYears()) ))
							{
								if(etapa.getAgendas()!=null)
								{
									for (Agenda agenda : etapa.getAgendas()) 
									{
										if(agenda.getVacunatorio().equals(v) && agenda.getDisponibilidadActual()>0)
												if(agenda.getDiasHoraLugar().getDiaHoraEspecifico()!=null)
												{
													Integer totDosis=etapa.getVacuna().getDosis().size();
													for (int i = nroDosisDesde; i < totDosis; i++) {
														
													
													for (DiaHora dh : agenda.getDiasHoraLugar().getDiaHoraEspecifico()) 
													{
														Calendar vti = Calendar.getInstance();
														Calendar vtf = Calendar.getInstance();
														Calendar hPref = Calendar.getInstance();
														hPref.setTime(dh.getDiaHoraEspecifico());
														if(horarioMatutino)
														{
															if(v.getHorarioMatutinoInicio()!=null)
																vti.setTime(v.getHorarioMatutinoInicio());
															else
																vti=null;
															
															
															if(v.getHorarioMatutinoFin()!=null)
																vtf.setTime(v.getHorarioMatutinoFin());
															else
																vtf=null;
														}
														else
														{
															
															if(v.getHorarioVespertinoInicio()!=null)
																vti.setTime(v.getHorarioVespertinoInicio());
															else
																vti=null;
														
															if(v.getHorarioVespertinoFin()!=null)
																vtf.setTime(v.getHorarioVespertinoFin());
															else
																vtf=null;
														}
														Boolean cumpleHorario=true;
														if(vti!=null || vtf!=null)
														{
															cumpleHorario=false;
															if(vti!=null)
															{
																vti.set(Calendar.DATE, hPref.get(Calendar.DATE));
																vti.set(Calendar.MONTH, hPref.get(Calendar.MONTH));
																vti.set(Calendar.YEAR, hPref.get(Calendar.YEAR));
																if(vti.compareTo(hPref)<=0 )
																{
																	cumpleHorario=true;
																}
																else
																	cumpleHorario=false;

															}
															if(vtf!=null)
															{
																vtf.set(Calendar.DATE, hPref.get(Calendar.DATE));
																vtf.set(Calendar.MONTH, hPref.get(Calendar.MONTH));
																vtf.set(Calendar.YEAR, hPref.get(Calendar.YEAR));
																if(vtf.compareTo(hPref)>=0 )
																{
																	cumpleHorario=true;
																}
																else
																	cumpleHorario=false;
															}
														
															
														}
														
														Boolean cumpleListado=true;
														List<String> listadoCi=etapa.getPublicoObjetivo();
														if(listadoCi!=null && listadoCi.size()>0)
																if(!listadoCi.contains(ci))
																	cumpleListado=false;
														
														if(cumpleHorario && cumpleListado)
															{
																
																DisponibilidadDTO dis= new DisponibilidadDTO();
																dis.setDepartamento(v.getDepartamento());
																dis.setDiaHora(dh.getDiaHoraEspecifico());
																dis.setDireccion(v.getDireccion());
																dis.setDosisNumero(lDiaHoraAsignar.size()+nroDosisDesde);
																dis.setEnfermedades(plan.getEnfermedadesString());
																dis.setEtapaNombre(etapa.getNombre());
																dis.setIdParaCancelarDisponibilidad(dh.getId());
																dis.setLatitud(v.getLatitud());
																dis.setLongitud(v.getLongitud());
																dis.setPlanNombre(plan.getNombre());
																dis.setVacuna(etapa.getVacuna().getNombre());
																dis.setLaboratorios(etapa.getVacuna().getLaboratoriosString());
																dis.setVacunatorioNombre(v.getNombre());
																disList.add(dis);
																lDiaHoraAsignar.add(dh);
																dh.setLugaresAsingados(dh.getLugaresAsingados()+1);
																
															}
															
														}
													}
													}
										if(lDiaHoraAsignar.size()>0)
										{
											agenda=em.merge(agenda);
											agenda.setDisponibilidadActual(agenda.getDisponibilidadActual()-lDiaHoraAsignar.size());
											
										}
									}
									if(lDiaHoraAsignar.size()>0)
										etapa=em.merge(etapa);
										etapa.setCupoRestante(etapa.getCupoRestante()-lDiaHoraAsignar.size());
									
									}					
								}	
							}
					
					}
		}
		
		
		return disList;
	}
	@Override
	public int liberarDisponibilidad(Integer idDisponibilidad) {
		// TODO Auto-generated method stub
		DiaHora dh= em.find(DiaHora.class, idDisponibilidad);
		if(dh==null)
			return 1;
		
			dh.setLugaresAsingados(dh.getLugaresAsingados()-1);
			
			Agenda a=dh.getDiaHoraLugar().getAgenda();
			
			a.setDisponibilidadActual(a.getDisponibilidadActual()+1);
			a=em.merge(a);
			Etapa e=a.getEtapa();
			e.setCupoRestante(e.getCupoRestante()+1);
			em.merge(e);
			return 0;
			
			
			
	}
	@Override
	public List<ReporteDTO> getReporteStockH() {
		// TODO Auto-generated method stub
		
		String query="Select  TO_CHAR(dh.diahoraespecifico, 'dd/mm/yyyy') as fecha, sum(dh.lugaresasingados) as usado,sum(dh.lugaresmaximo) as total, sum(dh.lugaresmaximo) -sum(dh.lugaresasingados) as stock from diahora dh inner join diahoralugar dhl on dhl.id=dh.diahoralugar_id inner join agenda a on dhl.agenda_id=a.id group by TO_CHAR(dh.diahoraespecifico, 'dd/mm/yyyy') order by fecha asc";
		Map<Date,Integer> result= new HashMap<Date, Integer>();
		Query queryNotAgruped=em.createNativeQuery(query);
			  
		@SuppressWarnings("unchecked")
		List<ReporteDTO> resNotA=  new ArrayList<ReporteDTO>();
		
		List<Object[]> l=  queryNotAgruped.getResultList();
		for (Object[] row : l) {
			ReporteDTO r= new ReporteDTO((String)row[0],((BigInteger)row[1]).intValue(),((BigInteger)row[2]).intValue(),((BigInteger)row[3]).intValue());
			resNotA.add(r);
		}
		
		return resNotA;
	}


}
