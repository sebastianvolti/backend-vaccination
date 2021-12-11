package uy.edu.fing.tse.vacunasuy.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="ExcepcionDiaHoraLugar.findAll",query="Select c  from ExcepcionDiaHoraLugar C  order by  c.id DESC")
	
	})
@Entity
public class ExcepcionDiaHoraLugar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@ManyToOne(fetch=FetchType.LAZY)
    Agenda agenda;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		ExcepcionDiaHoraLugar dat=(ExcepcionDiaHoraLugar) arg0;
			
		return (this.fecha.compareTo(dat.getFecha())==0);
		}
	catch(Exception e)
		{
		return false;
		}
	}
	
	
	
}