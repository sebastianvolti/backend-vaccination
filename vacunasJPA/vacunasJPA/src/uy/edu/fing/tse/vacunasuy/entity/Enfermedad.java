package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Enfermedad.findAll",query="Select c  from Enfermedad C  order by c.codigo ASC")
	})
@Entity
public class Enfermedad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    @Column(unique=true)
	private String codigo;
	
	private String nombre;


	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="Vacuna_Enfermedad", 
    joinColumns=@JoinColumn(name="enfermedad_id"),
    inverseJoinColumns=@JoinColumn(name="vacuna_id"))
	private List<EspecificacionVacuna> vacunas = new ArrayList<EspecificacionVacuna>();
	
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<EspecificacionVacuna> getVacunas() {
		return vacunas;
	}
	public void setVacunas(List<EspecificacionVacuna> vacunas) {
		this.vacunas = vacunas;
	}


	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		Enfermedad dat=(Enfermedad) arg0;
			
		return (this.codigo.compareTo(dat.getCodigo())==0);
		}
	catch(Exception e)
		{
		return false;
		}
	}

}