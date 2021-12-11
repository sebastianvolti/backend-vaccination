package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Agenda.findAll",query="Select c  from Agenda C  order by c.etapa.nombre DESC,  c.fechaApertura DESC"),
	@NamedQuery(name="Agenda.findAllByPlanId",query="Select c  from Agenda C where c.etapa.plan.id=:planId  order by c.etapa.nombre DESC,  c.fechaApertura DESC"),
	@NamedQuery(name="Agenda.findAllByVacunatorioIdByFecha",query="Select c  from Agenda C inner join c.diasHoraLugar dhl inner join dhl.diaHoraEspecifico dhe where dhe.diaHoraEspecifico=:fecha and c.vacunatorio.id=:vacunatorioId ")
	})
@Entity
public class Agenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    
	private String nombre;
	private Integer cupoHora;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaApertura;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCierre;

	private Integer disponibilidadMaxima=0;
	private Integer disponibilidadActual=0;
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "agenda",cascade = CascadeType.ALL,orphanRemoval = true)
	private DiaHoraLugar diasHoraLugar;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "agenda",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ExcepcionDiaHoraLugar> excepciones= new ArrayList<ExcepcionDiaHoraLugar>();
	

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "agenda",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Solicitud> solicitudes= new ArrayList<Solicitud>();
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Etapa etapa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Vacunatorio vacunatorio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public Etapa getEtapa() {
		return etapa;
	}
	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	public List<ExcepcionDiaHoraLugar> getExcepciones() {
		return excepciones;
	}
	public void setExcepciones(List<ExcepcionDiaHoraLugar> excepciones) {
		this.excepciones = excepciones;
	}
	public DiaHoraLugar getDiasHoraLugar() {
		return diasHoraLugar;
	}
	public void setDiasHoraLugar(DiaHoraLugar diasHoraLugar) {
		this.diasHoraLugar = diasHoraLugar;
	}
	public Integer getDisponibilidadMaxima() {
		return disponibilidadMaxima;
	}
	public void setDisponibilidadMaxima(Integer disponibilidadMaxima) {
		this.disponibilidadMaxima = disponibilidadMaxima;
	}
	public Integer getDisponibilidadActual() {
		return disponibilidadActual;
	}
	public void setDisponibilidadActual(Integer disponibilidadActual) {
		this.disponibilidadActual = disponibilidadActual;
	}
	public Integer getCupoHora() {
		return cupoHora;
	}
	public void setCupoHora(Integer cupoHora) {
		this.cupoHora = cupoHora;
	}
	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}
	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}
	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	
	
	
}