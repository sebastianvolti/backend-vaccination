package uy.edu.fing.tse.vacunasuy.implAP;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tse.vacunasuy.entity.Agenda;
import uy.edu.fing.tse.vacunasuy.entity.Enfermedad;
import uy.edu.fing.tse.vacunasuy.entity.EspecificacionDosis;
import uy.edu.fing.tse.vacunasuy.entity.EspecificacionVacuna;
import uy.edu.fing.tse.vacunasuy.entity.Laboratorio;
import uy.edu.fing.tse.vacunasuy.entity.Persona;
import uy.edu.fing.tse.vacunasuy.entity.Plan;
import uy.edu.fing.tse.vacunasuy.entity.Rol;
import uy.edu.fing.tse.vacunasuy.serviceAP.IEnfermedadesServiceRemote;
import uy.edu.fing.tse.vacunasuy.serviceAP.IPlanesServiceRemote;

@Stateless
@Remote (IEnfermedadesServiceRemote.class)
public class EnfermedadesService implements IEnfermedadesServiceRemote {

	@PersistenceContext(unitName = "vacunasuyJPA")
	private EntityManager em;
	@Override
	public List<Enfermedad> getEnfermedadesAll() {
		// TODO Auto-generated method stub
		List<Enfermedad> objList=null;
	Query query= em.createNamedQuery("Enfermedad.findAll");
	objList= query.getResultList();
	for (Enfermedad enf : objList) {
		if(enf.getVacunas()!=null)
			enf.getVacunas().size();
	}
	
		return objList;
	}

	@Override
	public List<EspecificacionVacuna> getVacunasAll() {
		// TODO Auto-generated method stub
		List<EspecificacionVacuna> objList=null;
	Query query= em.createNamedQuery("EspecificacionVacuna.findAll");
	objList= query.getResultList();
	for (EspecificacionVacuna obj : objList) {
		if(obj.getDosis()!=null)
		 obj.getDosis().size();
		if(obj.getLaboratorios()!=null)
			for (Laboratorio l : obj.getLaboratorios()) {
				l.getNombre();
			}
		
	}
	
		return objList;
	}

	@Override
	public Enfermedad guardarEnfermedad(Enfermedad enfermedadSeleccionada) {
		// TODO Auto-generated method stub
		Enfermedad p=em.merge(enfermedadSeleccionada);
		return getEnfermedadLazyById(p.getId());
	}

	@Override
	public Enfermedad getEnfermedadLazyById(Integer enfermedadId) {
		// TODO Auto-generated method stub
		Enfermedad p=em.find(Enfermedad.class, enfermedadId);
		
		if(p.getVacunas()!=null)
			for (EspecificacionVacuna e : p.getVacunas()) {
				if(e.getDosis()!=null)
				{
					for (EspecificacionDosis d : e.getDosis()) {
						d.getDiasEspera();
					}
				}
			}
		
		return  p;
	}

	@Override
	public void eliminarEnfermedad(Integer id) {
		// TODO Auto-generated method stub
		Enfermedad p=em.find(Enfermedad.class, id);
		if(p!=null)
			em.remove(p);
		
	}

	@Override
	public EspecificacionVacuna guardarVacuna(EspecificacionVacuna vacunaSeleccionada) {
		// TODO Auto-generated method stub
		EspecificacionVacuna v=em.merge(vacunaSeleccionada);
		return getVacunaLazyById(v.getId());
	}


	@Override
	public void eliminarVacuna(Integer id) {
		// TODO Auto-generated method stub
		EspecificacionVacuna p=em.find(EspecificacionVacuna.class, id);
		if(p!=null)
			em.remove(p);
		
		
	}

	@Override
	public EspecificacionVacuna getVacunaLazyById(Integer id) {
		// TODO Auto-generated method stub
		EspecificacionVacuna v= em.find(EspecificacionVacuna.class, id);
		if(v!=null)
		{
			if(v.getDosis()!=null)
				for (EspecificacionDosis ed : v.getDosis()) 
					ed.getDiasEspera();
				
			if(v.getLaboratorios()!=null)
				for (Laboratorio l : v.getLaboratorios()) 
					l.getNombre();
				
		}
		return v;
	}

	@Override
	public List<EspecificacionVacuna> getVacunasByEnfermedadId(Integer id) {
		// TODO Auto-generated method stub
		Enfermedad ef=em.find(Enfermedad.class, id);
		List<EspecificacionVacuna> objList=new ArrayList<EspecificacionVacuna>();
		
		if(ef!=null)
		{
			objList=ef.getVacunas();
			if(objList!=null)
			for (EspecificacionVacuna obj : objList) {
			
				if(obj.getDosis()!=null)
					obj.getDosis().size();
				if(obj.getLaboratorios()!=null)
					for (Laboratorio l : obj.getLaboratorios()) {
						l.getNombre();
					}
			}
		}
		
			return objList;
		
	}
}
