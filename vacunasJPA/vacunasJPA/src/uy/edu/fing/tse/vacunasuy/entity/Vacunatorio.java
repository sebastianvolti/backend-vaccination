package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Vacunatorio.findAll",query="Select c  from Vacunatorio C  order by c.id ASC"),
	@NamedQuery(name="Vacunatorio.findByToken",query="Select c  from Vacunatorio C where c.token=:token")
	})
@Entity
public class Vacunatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
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
	@Column(unique=true)
	private String token;
	
	
	@OneToMany(mappedBy="vacunatorio",fetch=FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<PuestoVacunacion> puestosVacunacion= new ArrayList<PuestoVacunacion>();
	
	@OneToMany(mappedBy="vacunatorio",fetch=FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Lote> lotes= new ArrayList<Lote>();
	
	@OneToMany(mappedBy="vacunatorio",fetch=FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ReservaVacunatorio> reservas= new ArrayList<ReservaVacunatorio>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<PuestoVacunacion> getPuestosVacunacion() {
		return puestosVacunacion;
	}
	public void setPuestosVacunacion(List<PuestoVacunacion> puestosVacunacion) {
		this.puestosVacunacion = puestosVacunacion;
	}
	public List<Lote> getLotes() {
		return lotes;
	}
	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}
	public List<ReservaVacunatorio> getReservas() {
		return reservas;
	}
	public void setReservas(List<ReservaVacunatorio> reservas) {
		this.reservas = reservas;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		Vacunatorio dat=(Vacunatorio) arg0;
			
		return (this.id.equals(dat.getId()));
		}
	catch(Exception e)
		{
		return false;
		}
	}

}