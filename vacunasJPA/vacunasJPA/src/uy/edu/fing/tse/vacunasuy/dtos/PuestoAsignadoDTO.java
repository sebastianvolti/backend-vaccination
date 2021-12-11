package uy.edu.fing.tse.vacunasuy.dtos;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;





public class PuestoAsignadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Date fecha;
	private Integer idPuesto;
	private String codigoPuesto;
	
	private Integer idVacunatorio;
	private String zona;
	private String nombre;
	private Time horarioMatutinoInicio;
	private Time horarioMatutinoFin;
	private Time horarioVespertinoInicio;
	private Time horarioVespertinoFin;
	private String direccion;
	private String departamento;
	private Double latitud;
	private Double longitud;
	
	private List<PlanDTO> planes;
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}
	public String getCodigoPuesto() {
		return codigoPuesto;
	}
	public void setCodigoPuesto(String codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}
	public Integer getIdVacunatorio() {
		return idVacunatorio;
	}
	public void setIdVacunatorio(Integer idVacunatorio) {
		this.idVacunatorio = idVacunatorio;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Time getHorarioMatutinoInicio() {
		return horarioMatutinoInicio;
	}
	public void setHorarioMatutinoInicio(Time horarioMatutinoInicio) {
		this.horarioMatutinoInicio = horarioMatutinoInicio;
	}
	public Time getHorarioMatutinoFin() {
		return horarioMatutinoFin;
	}
	public void setHorarioMatutinoFin(Time horarioMatutinoFin) {
		this.horarioMatutinoFin = horarioMatutinoFin;
	}
	public Time getHorarioVespertinoInicio() {
		return horarioVespertinoInicio;
	}
	public void setHorarioVespertinoInicio(Time horarioVespertinoInicio) {
		this.horarioVespertinoInicio = horarioVespertinoInicio;
	}
	public Time getHorarioVespertinoFin() {
		return horarioVespertinoFin;
	}
	public void setHorarioVespertinoFin(Time horarioVespertinoFin) {
		this.horarioVespertinoFin = horarioVespertinoFin;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Double getLatitud() {
		return latitud;
	}
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	
	
	public List<PlanDTO> getPlanes() {
		return planes;
	}
	public void setPlanes(List<PlanDTO> planes) {
		this.planes = planes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}