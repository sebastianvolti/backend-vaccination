package uy.edu.fing.tse.vacunasuy.serviceNP.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import uy.edu.fing.tse.vacunasuy.entity.EspecificacionVacuna;
import uy.edu.fing.tse.vacunasuy.entity.Laboratorio;
import uy.edu.fing.tse.vacunasuy.entity.Lote;
import uy.edu.fing.tse.vacunasuy.entity.LoteDosis;
import uy.edu.fing.tse.vacunasuy.entity.PuestoVacunacion;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.SocioLogistico;
import uy.edu.fing.tse.vacunasuy.entity.VacunadorPuestoAsignado;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;
import uy.edu.fing.tse.vacunasuy.serviceNP.ILaboratoriosServiceRemote;
import uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceLocal;
import uy.edu.fing.tse.vacunasuy.serviceNP.IVacunatoriosServiceRemote;



@Stateless
@Remote (ILaboratoriosServiceRemote.class)

public class LaboratoriosService implements ILaboratoriosServiceRemote{
	@PersistenceContext(unitName = "vacunasuyJPA")
	private EntityManager em;
	
	@Override
	public List<Laboratorio> getLaboratoriosAll() {
		// TODO Auto-generated method stub
		List<Laboratorio> objList=null;
	Query query= em.createNamedQuery("Laboratorio.findAll");
	objList= query.getResultList();
	for (Laboratorio enf : objList) {
	
	}
	
		return objList;
	}

	@Override
	public Laboratorio guardarLaboratorio(Laboratorio laboratorioSeleccionado) {
		// TODO Auto-generated method stub
	try
	{
		Laboratorio l=em.merge(laboratorioSeleccionado);
		return getLaboratorioLazyById(l.getId());
	}
	catch (Exception e) {
		// TODO: handle exception
		return null;
	}
		
	}

	@Override
	public Boolean eliminarLaboratorio(Integer id) {
		// TODO Auto-generated method stub
		Laboratorio l=em.find(Laboratorio.class,id);
			try
			{
				em.remove(l);
				return true;
			}
			catch (Exception e) {
				return false;
			}
		
	}

	@Override
	public Laboratorio getLaboratorioLazyById(Integer id) {
		// TODO Auto-generated method stub
		Laboratorio l=em.find(Laboratorio.class,id);
		if(l!=null)
		{
			if(l.getVacunas()!=null)
				for (EspecificacionVacuna v : l.getVacunas()) {
					v.getNombre();
				}
		}
			return l;
	}


}
