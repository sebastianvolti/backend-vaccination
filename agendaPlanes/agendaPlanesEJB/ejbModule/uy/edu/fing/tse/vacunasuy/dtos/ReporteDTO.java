package uy.edu.fing.tse.vacunasuy.dtos;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;


public class ReporteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
private String fecha;
private Integer usado;
private Integer total;
private Integer stock;

public ReporteDTO(String fecha, Integer usado, Integer total, Integer stock) {
	super();
	this.fecha = fecha;
	this.usado = usado;
	this.total = total;
	this.stock = stock;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public Integer getUsado() {
	return usado;
}
public void setUsado(Integer usado) {
	this.usado = usado;
}
public Integer getTotal() {
	return total;
}
public void setTotal(Integer total) {
	this.total = total;
}
public Integer getStock() {
	return stock;
}
public void setStock(Integer stock) {
	this.stock = stock;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

}