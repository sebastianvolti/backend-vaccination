package uy.edu.fing.tse.vacunasuy.ws.dto;

import java.util.Date;

public class LoteDosis {

	private String codigo;
	private Integer cantidad;
	private Date fecha;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
