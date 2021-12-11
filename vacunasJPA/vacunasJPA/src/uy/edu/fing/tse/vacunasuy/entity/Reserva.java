package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Reserva.findAll",query="Select c  from Reserva C  order by c.id ASC")
	})
@Entity
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String status;
	
	
	


	@ManyToOne(fetch=FetchType.LAZY)
	private Solicitud solicitud;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private EspecificacionDosis especificacionDosis;

	@OneToMany(mappedBy="reserva",fetch=FetchType.LAZY)
	private List<ReservaVacunatorio> reservaVacunatorio= new ArrayList<ReservaVacunatorio>();
	
	
	public Reserva() {
		
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Solicitud getSolicitud() {
		return solicitud;
	}


	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}


	public EspecificacionDosis getEspecificacionDosis() {
		return especificacionDosis;
	}


	public void setEspecificacionDosis(EspecificacionDosis especificacionDosis) {
		this.especificacionDosis = especificacionDosis;
	}


	public List<ReservaVacunatorio> getReservaVacunatorio() {
		return reservaVacunatorio;
	}


	public void setReservaVacunatorio(List<ReservaVacunatorio> reservaVacunatorio) {
		this.reservaVacunatorio = reservaVacunatorio;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}