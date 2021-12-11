package uy.edu.fing.tse.vacunasuy.serviceNP;

import java.util.Date;
import java.util.List;

import uy.edu.fing.tse.vacunasuy.entity.Lote;
import uy.edu.fing.tse.vacunasuy.entity.PuestoVacunacion;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;




public interface IVacunatoriosServiceLocal {
	List<Vacunatorio> getVacunatoriosAll();
	List<PuestoVacunacion> obtenerVacunadoresPuesto(Integer idVacunatorio,Date fecha);

	List<PuestoVacunacion> obtenerVacunadoresPuesto(Integer idVacunatorio);

	int dosisSuministradas(String codigoLote,  Date fecha,List<String> ciList,Integer etapaId,  Integer vacunatorioId);

	List<ReservaVacunatorio> obtenerReservasVacunatorio(Integer idVacunatorio, Date fecha);

		void setVacunadoresEnPuestoFecha(List<String> vacunadoresCi, String puestoCodigo, Date fecha,Integer idVacunatorio);
	Integer getVacunatorioIdByToken(String token);
	
		
}
