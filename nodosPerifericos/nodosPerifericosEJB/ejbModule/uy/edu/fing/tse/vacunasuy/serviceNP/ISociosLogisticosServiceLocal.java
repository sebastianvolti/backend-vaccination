package uy.edu.fing.tse.vacunasuy.serviceNP;

import java.util.Date;
import java.util.List;

import uy.edu.fing.tse.vacunasuy.entity.Lote;
import uy.edu.fing.tse.vacunasuy.entity.PuestoVacunacion;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;




public interface ISociosLogisticosServiceLocal {
	Integer recibirLote(String codigo, Integer cantidad, Integer idSocioLogistico);

	Integer transporteLote(String codigo, Integer cantidad, Integer idSocioLogistico);
	Lote getLoteLazyById(Integer id);
	Integer getSocioLogisticoIdByToken(String token);

	List<Lote> getLotesTransito(Integer idSl);

	List<Lote> getLotesEntregado(Integer idSl);

	List<Lote> getLotesPendiente(Integer idSl);
		
	
		
}
