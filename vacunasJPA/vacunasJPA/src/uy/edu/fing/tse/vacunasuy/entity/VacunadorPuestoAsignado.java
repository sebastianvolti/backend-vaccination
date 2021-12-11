package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="VacunadorPuestoAsignado.findAll",query="Select c  from VacunadorPuestoAsignado C  order by c.id ASC"),
	@NamedQuery(name="VacunadorPuestoAsignado.findAllByVac",query="Select c  from VacunadorPuestoAsignado C join c.puestoTrabajoAsignado pva join c.vacunadorAsignado va where pva.vacunatorio.id=:idVacunatorio order  by c.fecha desc, pva.codigo, va.ci"),
	@NamedQuery(name="VacunadorPuestoAsignado.findAllByVacByDate",query="Select c  from VacunadorPuestoAsignado C join c.puestoTrabajoAsignado pva join c.vacunadorAsignado va where pva.vacunatorio.id=:idVacunatorio and c.fecha=:fecha  order by pva.codigo, va.ci"),
@NamedQuery(name="VacunadorPuestoAsignado.findAllByCiByFecha",query="Select c  from VacunadorPuestoAsignado C  where c.vacunadorAsignado.ci=:ci and c.fecha>=:fecha  order by c.fecha asc")
	
})

@Entity
public class VacunadorPuestoAsignado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private PuestoVacunacion puestoTrabajoAsignado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Persona vacunadorAsignado;
	
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
	public PuestoVacunacion getPuestoTrabajoAsignado() {
		return puestoTrabajoAsignado;
	}
	public void setPuestoTrabajoAsignado(PuestoVacunacion puestoTrabajoAsignado) {
		this.puestoTrabajoAsignado = puestoTrabajoAsignado;
	}
	public Persona getVacunadorAsignado() {
		return vacunadorAsignado;
	}
	public void setVacunadorAsignado(Persona vacunadorAsignado) {
		this.vacunadorAsignado = vacunadorAsignado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}