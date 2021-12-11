package uy.edu.fing.tse.vacunasuy.serviceNP.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import uy.edu.fing.tse.vacunasuy.entity.Lote;
import uy.edu.fing.tse.vacunasuy.entity.LoteDosis;
import uy.edu.fing.tse.vacunasuy.entity.SocioLogistico;
import uy.edu.fing.tse.vacunasuy.serviceNP.ISociosLogisticosServiceLocal;
import uy.edu.fing.tse.vacunasuy.serviceNP.ISociosLogisticosServiceRemote;



@Stateless
@Remote (ISociosLogisticosServiceRemote.class)
@Local (ISociosLogisticosServiceLocal.class)
public class SociosLogisticosService implements ISociosLogisticosServiceRemote,ISociosLogisticosServiceLocal{
	@PersistenceContext(unitName = "vacunasuyJPA")
	private EntityManager em;
	
	@Override
	public List<SocioLogistico> getSociosLogisticosAll() {
		// TODO Auto-generated method stub
		List<SocioLogistico> objList=null;
	Query query= em.createNamedQuery("SocioLogistico.findAll");
	objList= query.getResultList();
	for (SocioLogistico enf : objList) {
	
	}
	
		return objList;
	}

	@Override
	public SocioLogistico guardarSocioLogistico(SocioLogistico socioLogisticoSeleccionado) {
		// TODO Auto-generated method stub
		try
		{
			SocioLogistico l=em.merge(socioLogisticoSeleccionado);
			return getSocioLogisticoLazyById(l.getId());
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public Boolean eliminarSocioLogistico(Integer id) {
		// TODO Auto-generated method stub
		SocioLogistico l=em.find(SocioLogistico.class,id);
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
	public SocioLogistico getSocioLogisticoLazyById(Integer id) {
		// TODO Auto-generated method stub
		SocioLogistico l=em.find(SocioLogistico.class,id);
		if(l!=null)
		{
			
		}
			return l;
	}

	

	@Override
	public List<Lote> getLotesAll() {
		// TODO Auto-generated method stub
		List<Lote> objList=null;
	Query query= em.createNamedQuery("Lote.findAll");
	objList= query.getResultList();
	for (Lote enf : objList) {
		if(enf.getVacunatorio()!=null)
			enf.getVacunatorio().getNombre();
		if(enf.getSocioLogistico()!=null)
			enf.getSocioLogistico().getCodigo();
	}
	
		return objList;
	}

	@Override
	public Lote guardarLote(Lote loteSeleccionado) {
		// TODO Auto-generated method stub
	try
	{
		Lote l=em.merge(loteSeleccionado);
		return getLoteLazyById(l.getId());
	}
	catch (Exception e) {
		// TODO: handle exception
		return null;
	}
		
	}

	@Override
	public Boolean eliminarLote(Integer id) {
		// TODO Auto-generated method stub
		Lote l=em.find(Lote.class,id);
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
	public Lote getLoteLazyById(Integer id) {
		// TODO Auto-generated method stub
		Lote l=em.find(Lote.class,id);
		if(l!=null)
		{
			if(l.getVacunatorio()!=null)
				l.getVacunatorio().getNombre();

			if(l.getSocioLogistico()!=null)
				l.getSocioLogistico().getCodigo();
			if(l.getDosis()!=null)
				for (LoteDosis dl : l.getDosis()) {
					dl.getCodigo();
				}
		}
			return l;
	}

	
	@Override
	public Integer getSocioLogisticoIdByToken(String token) {
		// TODO Auto-generated method stub

		Query query= em.createNamedQuery("SocioLogistico.findByToken");
		query.setParameter("token",token);
		
		SocioLogistico v=null;
		try
		{
			v=(SocioLogistico) query.getSingleResult();
			return v.getId();
		}
		catch (NoResultException e) {
			// TODO: handle exception
			return null;
		}
	}


	@Override
	public Integer recibirLote(String codigo, Integer cantidad,  Integer idSL) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("Lote.findByCodigo");
		query.setParameter("codigo", codigo);
		Date fecha= new Date();
		try
		{
			Lote l= (Lote) query.getSingleResult();
			l.setEstado("Entregado");
			l.setFecha(fecha);
			l.setCant(cantidad);
			
			SocioLogistico sl=em.find(SocioLogistico.class, idSL);
			sl.getLotes().add(l);
			l.setSocioLogistico(sl);
			
			em.merge(l);
			return 0;
			
		}
		catch(NoResultException e)
		{
			return -1;
		}
	
	}

	@Override
	public Integer transporteLote(String codigo, Integer cantidad, Integer idSL) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("Lote.findByCodigo");
		query.setParameter("codigo", codigo);
		Date fecha= new Date();
		try
		{
			Lote l= (Lote) query.getSingleResult();
			l.setEstado("Transito");
			l.setFecha(fecha);
			l.setCant(cantidad);
			
			SocioLogistico sl=em.find(SocioLogistico.class, idSL);
			sl.getLotes().add(l);
			l.setSocioLogistico(sl);
			
			em.merge(l);
			return 0;
			
		}
		catch(NoResultException e)
		{
			return -1;
		}
	
	}
	@Override
	
	public List<Lote> getLotesPendiente(Integer idSl) {
		// TODO Auto-generated method stub
		Query query= em.createNamedQuery("Lote.findPendienteBySocioLogisticoId");
		query.setParameter("socioLogisticoId",idSl);
		
		List<Lote> ls=query.getResultList();
		if(ls!=null)
		for (Lote lote : ls) 
			if(lote.getVacunatorio()!=null)
				lote.getVacunatorio().getId();
		
			
		
		return ls;
	}
	@Override
	public List<Lote> getLotesEntregado(Integer idSl) {
		// TODO Auto-generated method stub
		Query query= em.createNamedQuery("Lote.findEntregadoBySocioLogisticoId");
		query.setParameter("socioLogisticoId",idSl);
		
		List<Lote> ls=query.getResultList();
		if(ls!=null)
		for (Lote lote : ls) 
			if(lote.getVacunatorio()!=null)
				lote.getVacunatorio().getId();
		
			
		
		return ls;
	}
	@Override
	public List<Lote> getLotesTransito(Integer idSl) {
		// TODO Auto-generated method stub
		Query query= em.createNamedQuery("Lote.findTransitoBySocioLogisticoId");
		query.setParameter("socioLogisticoId",idSl);
		
		List<Lote> ls=query.getResultList();
		if(ls!=null)
		for (Lote lote : ls) 
			if(lote.getVacunatorio()!=null)
				lote.getVacunatorio().getId();
		
			
		
		return ls;
	}
}
