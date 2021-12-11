package uy.edu.fing.tse.vacunasuy.ws.dto;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




public class VacunatorioData
{
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
	
	
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
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
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
}