package uy.edu.fing.tse.vacunasuy.entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.*;


@NamedQueries({	@NamedQuery(name="DiaHora.findAll",query="Select c  from DiaHora C  order by  c.id DESC"),
	
	@NamedQuery(name="DiaHora.findByDiaHoraEspecificoByLugar",query="Select c  from DiaHora C where  c.diaHoraEspecifico=:fecha and c.diaHoraLugar.vacunatorio.id=:vacunatorioId order by  c.id DESC")
	})
@Entity
public class DiaHora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    
	@Transient
	private Boolean eliminar=false;
	@Temporal(TemporalType.TIMESTAMP)
	private Date diaHoraEspecifico;
	
	private Integer lugaresAsingados=0;
	private Integer lugaresMaximo=0;
	
    @ManyToOne(fetch=FetchType.LAZY)
    DiaHoraLugar diaHoraLugar;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public DiaHoraLugar getDiaHoraLugar() {
		return diaHoraLugar;
	}
	public void setDiaHoraLugar(DiaHoraLugar diaHoraLugar) {
		this.diaHoraLugar = diaHoraLugar;
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		DiaHora dat=(DiaHora) arg0;
			
		return (this.diaHoraEspecifico.compareTo(dat.getDiaHoraEspecifico())==0);
		}
	catch(Exception e)
		{
		return false;
		}
	}
	public Date getDiaHoraEspecifico() {
		return diaHoraEspecifico;
	}
	public void setDiaHoraEspecifico(Date diaHoraEspecifico) {
		this.diaHoraEspecifico = diaHoraEspecifico;
	}
	public Integer getLugaresAsingados() {
		return lugaresAsingados;
	}
	public void setLugaresAsingados(Integer lugaresAsingados) {
		this.lugaresAsingados = lugaresAsingados;
	}
	public Boolean getEliminar() {
		return eliminar;
	}
	public void setEliminar(Boolean eliminar) {
		this.eliminar = eliminar;
	}
	public Integer getLugaresMaximo() {
		return lugaresMaximo;
	}
	public void setLugaresMaximo(Integer lugaresMaximo) {
		this.lugaresMaximo = lugaresMaximo;
	}
	
	
	
}