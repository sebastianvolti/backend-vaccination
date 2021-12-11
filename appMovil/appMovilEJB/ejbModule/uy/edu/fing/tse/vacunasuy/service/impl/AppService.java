package uy.edu.fing.tse.vacunasuy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tse.vacunasuy.entity.Lote;
import uy.edu.fing.tse.vacunasuy.entity.LoteDosis;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.SocioLogistico;
import uy.edu.fing.tse.vacunasuy.entity.VacunadorPuestoAsignado;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;
import uy.edu.fing.tse.vacunasuy.service.IAppServiceLocal;



@Stateless
public class AppService implements IAppServiceLocal {

	@PersistenceContext(unitName = "vacunasuyJPA")
	private EntityManager em;

	

	@Override
	public List<ReservaVacunatorio> obtenerReservasCompletadasVacunatorio(String ci) {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("ReservaVacunatorio.findAllByCiCompleted");
		query.setParameter("ci", ci);
		
		
		List<ReservaVacunatorio> lst=query.getResultList();
		if (lst!=null)
		for (ReservaVacunatorio l : lst) {
			if(l.getVacunatorio()!=null)
				l.getVacunatorio().getId();
			if(l.getReserva()!=null)
			{
				l.getReserva().getId();
				if(l.getReserva().getEspecificacionDosis()!=null)
				{
					l.getReserva().getEspecificacionDosis().getId();
					if(l.getReserva().getEspecificacionDosis().getVacuna()!=null)
						{
							l.getReserva().getEspecificacionDosis().getVacuna().getId();
							if(l.getReserva().getEspecificacionDosis().getVacuna().getEnfermedades()!=null)
								l.getReserva().getEspecificacionDosis().getVacuna().getEnfermedades().size();
							if(l.getReserva().getEspecificacionDosis().getVacuna().getLaboratorios()!=null)
								l.getReserva().getEspecificacionDosis().getVacuna().getLaboratoriosString();
						}
				}
				if(l.getReserva().getSolicitud()!=null)
				{
				
					l.getReserva().getSolicitud().getId();
					if(l.getReserva().getSolicitud().getPersona()!=null)
					l.getReserva().getSolicitud().getPersona().getId();
					if(l.getReserva().getSolicitud().getAgenda()!=null)
						if(l.getReserva().getSolicitud().getAgenda().getEtapa()!=null)
							if(l.getReserva().getSolicitud().getAgenda().getEtapa().getPlan()!=null)
								l.getReserva().getSolicitud().getAgenda().getEtapa().getPlan().getNombre();
				}
			}
		}
		return lst;
	}



	@Override
	public List<Vacunatorio> obtenerVacunatorios() {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery("Vacunatorio.findAll");
		
		
		return query.getResultList();
	}

	


}
