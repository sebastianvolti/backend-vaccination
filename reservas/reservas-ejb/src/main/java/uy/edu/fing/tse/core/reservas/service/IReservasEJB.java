package uy.edu.fing.tse.core.reservas.service;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uy.edu.fing.tse.core.reservas.dto.DateHistorgramDTO;
import uy.edu.fing.tse.core.reservas.dto.ReservaDTO;
import uy.edu.fing.tse.core.reservas.dto.ResultReservas;
import uy.edu.fing.tse.core.reservas.dto.SolicitudDTO;

public interface IReservasEJB {

	int liberarCupos(Long idAgenda, Long cantCupos);

	ResultReservas getReservasByAgenda(Long idAgenda);

	DateHistorgramDTO getDateHistogram();

	DateHistorgramDTO getDateHistogramVacunatorio(Long idVacunatorio);

	XSSFWorkbook buildDownloadEmptyExcel(Long idVacunatorio, String day);

	void parseAndExecutoUploadExcel(XSSFWorkbook workbook, Long idVacunatorio) throws Exception;

	void sendReservaEmail(SolicitudDTO solicitud, ReservaDTO reserva);

	List<ReservaDTO> getReservasByVacunatorioAndDay(Long idVacunatorio, String day);

	List<ReservaDTO> getReservasByCodigo(String codigo);
}
