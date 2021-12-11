package uy.edu.fing.tse.vacunasuy.serviceNP;

import java.util.List;


import uy.edu.fing.tse.vacunasuy.entity.Lote;
import uy.edu.fing.tse.vacunasuy.entity.SocioLogistico;





public interface ISociosLogisticosServiceRemote {
	List<SocioLogistico> getSociosLogisticosAll();
	SocioLogistico guardarSocioLogistico(SocioLogistico sLSeleccionado);

	Boolean eliminarSocioLogistico(Integer id);

	SocioLogistico getSocioLogisticoLazyById(Integer id);
		
	
	List<Lote> getLotesAll();
	Lote guardarLote(Lote loteSeleccionado);

	Boolean eliminarLote(Integer id);

	Lote getLoteLazyById(Integer id);
		

		
}
