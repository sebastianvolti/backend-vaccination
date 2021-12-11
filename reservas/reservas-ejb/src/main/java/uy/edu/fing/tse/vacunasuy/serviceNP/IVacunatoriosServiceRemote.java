package uy.edu.fing.tse.vacunasuy.serviceNP;

import java.util.Date;
import java.util.List;

import uy.edu.fing.tse.vacunasuy.entity.PuestoVacunacion;
import uy.edu.fing.tse.vacunasuy.entity.ReservaVacunatorio;
import uy.edu.fing.tse.vacunasuy.entity.Vacunatorio;

public interface IVacunatoriosServiceRemote {
	List<Vacunatorio> getVacunatoriosAll();

	List<PuestoVacunacion> obtenerVacunadoresPuesto(Integer idVacunatorio,Date fecha);

	List<PuestoVacunacion> obtenerVacunadoresPuesto(Integer idVacunatorio);

	int dosisSuministradas(String codigo, Integer cantidad, String fecha);

	List<ReservaVacunatorio> obtenerReservasVacunatorio(Integer idVacunatorio, Date fecha);

	Integer recibirLote(String codigo, Integer cantidad, String codigoSL, Long idVacunatorio);

	Integer transporteLote(String codigo, Integer cantidad, String codigoSL, Long idVacunatorio);
	Vacunatorio getVacunatorioLazyById(Integer id);
	Boolean eliminarVacunatorio(Integer id);
	Vacunatorio guardarVacunatorio(Vacunatorio vacunatorioSeleccionado);

}
