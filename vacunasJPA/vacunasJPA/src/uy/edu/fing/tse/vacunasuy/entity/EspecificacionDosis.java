package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="EspecificacionDosis.findAll",query="Select c  from EspecificacionDosis C  order by c.id ASC")
	})
@Entity
public class EspecificacionDosis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer diasEspera;


	@ManyToOne(fetch=FetchType.LAZY)
	private EspecificacionVacuna vacuna;

	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getDiasEspera() {
		return diasEspera;
	}


	public void setDiasEspera(Integer diasEspera) {
		this.diasEspera = diasEspera;
	}


	public EspecificacionVacuna getVacuna() {
		return vacuna;
	}


	public void setVacuna(EspecificacionVacuna vacuna) {
		this.vacuna = vacuna;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}