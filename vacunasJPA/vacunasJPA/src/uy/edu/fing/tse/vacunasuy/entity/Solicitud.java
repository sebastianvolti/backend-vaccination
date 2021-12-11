package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Solicitud.findAll",query="Select c  from Solicitud C  order by c.id ASC")
	})
@Entity
public class Solicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    @Column(unique=true)
	private String codigo;
    private String zona;
	private String status;
	private String turno;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	
	@OneToMany(mappedBy="solicitud",fetch=FetchType.LAZY,  orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Reserva> reservas= new ArrayList<Reserva>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Persona persona;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Agenda agenda;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
}