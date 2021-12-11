package uy.edu.fing.tse.vacunasuy.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.VacunadorPuestoAsignado;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;



@Local
public interface IAppServiceLocal {

	List<ReservaVacunatorio> obtenerReservasCompletadasVacunatorio(String ci);

	List<Vacunatorio> obtenerVacunatorios();
	
		
}
