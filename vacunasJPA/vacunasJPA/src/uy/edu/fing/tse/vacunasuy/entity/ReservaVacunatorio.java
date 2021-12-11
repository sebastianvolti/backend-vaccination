package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="ReservaVacunatorio.findAll",query="Select c  from ReservaVacunatorio C  order by c.id ASC"),
	
	
	@NamedQuery(name="ReservaVacunatorio.findAllByVacByDate",query="Select c  from ReservaVacunatorio C join c.vacunatorio v join c.reserva r join r.solicitud s join r.especificacionDosis ed join ed.vacuna ev join s.persona p where c.fecha=:fecha and v.id=:idVacunatorio order by c.id ASC"),
	@NamedQuery(name="ReservaVacunatorio.findAllByCiCompleted",query="Select c  from ReservaVacunatorio C join c.vacunatorio v join c.reserva r join r.solicitud s join r.especificacionDosis ed join ed.vacuna ev join s.persona p where p.ci=:ci and c.status='Completado' order by c.id ASC")

	
})
@Entity
public class ReservaVacunatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	private String status;
	private Integer numeroDosis;
	
	
	


	@ManyToOne(fetch=FetchType.LAZY)
	private Vacunatorio vacunatorio;

	@ManyToOne(fetch=FetchType.LAZY)
	private Reserva reserva;
	


	
	public ReservaVacunatorio() {
		
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public Date getFecha() {
		return fecha;
	}




	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}




	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}




	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}




	public Reserva getReserva() {
		return reserva;
	}




	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Integer getNumeroDosis() {
		return numeroDosis;
	}




	public void setNumeroDosis(Integer numeroDosis) {
		this.numeroDosis = numeroDosis;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}
	
}