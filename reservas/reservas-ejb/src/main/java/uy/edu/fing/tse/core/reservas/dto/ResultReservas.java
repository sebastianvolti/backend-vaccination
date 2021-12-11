package uy.edu.fing.tse.core.reservas.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultReservas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ReservaDTO> reservas;

	public ResultReservas() {
		this.reservas = new ArrayList<>();
	}

	public ResultReservas(List<ReservaDTO> reservas) {
		this.reservas = reservas;
	}

	public List<ReservaDTO> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<ReservaDTO> reservas) {
		this.reservas = reservas;
	}
}
