package uy.edu.fing.tse.vacunasuy.serviceAP;

import java.util.List;

import uy.edu.fing.tse.vacunasuy.entity.Enfermedad;
import uy.edu.fing.tse.vacunasuy.entity.EspecificacionVacuna;
import uy.edu.fing.tse.vacunasuy.entity.Plan;




public interface IEnfermedadesServiceRemote {
	List<Enfermedad> getEnfermedadesAll();
	List<EspecificacionVacuna> getVacunasAll();
	Enfermedad guardarEnfermedad(Enfermedad enfermedadSeleccionada);
	Enfermedad getEnfermedadLazyById(Integer enfermedadId);
	void eliminarEnfermedad(Integer id);
	EspecificacionVacuna guardarVacuna(EspecificacionVacuna vacunaSeleccionada);
	List<EspecificacionVacuna> getVacunasByEnfermedadId(Integer id);
	
	void eliminarVacuna(Integer id);
	EspecificacionVacuna getVacunaLazyById(Integer id);
	
}
