package uy.edu.fing.tse.core.reservas.dto;

import java.time.LocalDateTime;

public class ReservaVacunatorioDTO {

	private LocalDateTime fecha;
	private Long vacunatorioId;



	public ReservaVacunatorioDTO() {
		super();
	}
	public ReservaVacunatorioDTO(LocalDateTime fecha, Long vacunatorioId) {
		super();
		this.fecha = fecha;
		this.vacunatorioId = vacunatorioId;
	}
	public LocalDateTime getFecha() {
		return this.fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public Long getVacunatorioId() {
		return this.vacunatorioId;
	}
	public void setVacunatorioId(Long vacunatorioId) {
		this.vacunatorioId = vacunatorioId;
	}


}
