package uy.edu.fing.tse.core.reservas.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import uy.edu.fing.tse.core.reservas.dto.ReservaDTO;

public interface IReservaDAO {

	void crearReserva(ReservaDTO reserva);

	void actualizarReserva(ReservaDTO reserva);

	ReservaDTO getReservaByCodigoAndDosis(String codigo, Integer dosis);

	List<ReservaDTO> getReservasByAgenda(Long idAgenda);

	Map<String, Long> getDateHistogram(boolean lastMonth);

	Map<String, Long> getDateHistogramVacunatorio(Long vacunatorioId);

	List<ReservaDTO> getReservasPendingByVacunatorioAndDay(Long idVacunatorio, LocalDate day);

	List<ReservaDTO> getReservasByVacunatorioAndDay(Long idVacunatorio, LocalDate day);

	List<ReservaDTO> getReservasByCodigo(String codigo);

	void eliminarReservasByCodigo(String codigo);

}
