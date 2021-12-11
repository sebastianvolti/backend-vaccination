package uy.edu.fing.tse.vacunasuy.serviceNP;

import java.util.Date;
import java.util.List;

import uy.edu.fing.tse.vacunasuy.entity.Laboratorio;
import uy.edu.fing.tse.vacunasuy.entity.PuestoVacunacion;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;




public interface ILaboratoriosServiceRemote {
	List<Laboratorio> getLaboratoriosAll();
	Laboratorio guardarLaboratorio(Laboratorio laboratorioSeleccionado);

	Boolean eliminarLaboratorio(Integer id);

	Laboratorio getLaboratorioLazyById(Integer id);
		
		
}
