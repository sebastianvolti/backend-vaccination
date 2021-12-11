package uy.edu.fing.tse.vacunasuy.ws.dto;

import java.util.Date;
import java.util.List;

public class LoteData {

	private String codigo;
	private Integer cantidad;
	private Date fecha;
	private Integer etapaId;
	private String estado;
	private Integer vacunatorioId;
	private String vacunatorioNombre;
	private String vacunatorioDireccion;
	private Double vacunatorioLatitud;
	private Double vacunatorioLongitud;
	private String vacunatorioDepartamento;
	private List<String> ciList;
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
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getVacunatorioId() {
		return vacunatorioId;
	}
	public void setVacunatorioId(Integer vacunatorioId) {
		this.vacunatorioId = vacunatorioId;
	}
	public String getVacunatorioNombre() {
		return vacunatorioNombre;
	}
	public void setVacunatorioNombre(String vacunatorioNombre) {
		this.vacunatorioNombre = vacunatorioNombre;
	}
	public String getVacunatorioDireccion() {
		return vacunatorioDireccion;
	}
	public void setVacunatorioDireccion(String vacunatorioDireccion) {
		this.vacunatorioDireccion = vacunatorioDireccion;
	}
	public Double getVacunatorioLatitud() {
		return vacunatorioLatitud;
	}
	public void setVacunatorioLatitud(Double vacunatorioLatitud) {
		this.vacunatorioLatitud = vacunatorioLatitud;
	}
	public Double getVacunatorioLongitud() {
		return vacunatorioLongitud;
	}
	public void setVacunatorioLongitud(Double vacunatorioLongitud) {
		this.vacunatorioLongitud = vacunatorioLongitud;
	}
	public String getVacunatorioDepartamento() {
		return vacunatorioDepartamento;
	}
	public void setVacunatorioDepartamento(String vacunatorioDepartamento) {
		this.vacunatorioDepartamento = vacunatorioDepartamento;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getEtapaId() {
		return etapaId;
	}
	public void setEtapaId(Integer etapaId) {
		this.etapaId = etapaId;
	}
	public List<String> getCiList() {
		return ciList;
	}
	public void setCiList(List<String> ciList) {
		this.ciList = ciList;
	}
	
	
	
	
}
