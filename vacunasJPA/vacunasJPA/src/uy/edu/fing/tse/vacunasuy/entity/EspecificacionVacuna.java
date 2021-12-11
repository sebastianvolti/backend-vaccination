package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="EspecificacionVacuna.findAll",query="Select c  from EspecificacionVacuna C  order by  c.nombre ASC"),
	@NamedQuery(name="EspecificacionVacuna.findAllByEnfermedadId",query="Select distinct c  from EspecificacionVacuna C inner join c.enfermedades e where e.id=:enfermedadId order by  c.nombre ASC")
	
	})
@Entity
public class EspecificacionVacuna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	@Transient
	private String laboratoriosString;
	private Integer diasDeInmunizacion;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Vacuna_Laboratorio", 
    joinColumns=@JoinColumn(name="vacuna_id"),
    inverseJoinColumns=@JoinColumn(name="laboratorio_id"))
	private List<Laboratorio> laboratorios=new ArrayList<Laboratorio>();
	
	
	@OneToMany(mappedBy="vacuna",fetch=FetchType.LAZY,  orphanRemoval = true, cascade = CascadeType.ALL)
	private List<EspecificacionDosis> dosis=  new ArrayList<EspecificacionDosis>();

	@ManyToMany(mappedBy="vacunas",fetch=FetchType.LAZY)
	private List<Enfermedad> enfermedades=  new ArrayList<Enfermedad>();
	


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}







	public List<Laboratorio> getLaboratorios() {
		return laboratorios;
	}



	public void setLaboratorios(List<Laboratorio> laboratorios) {
		this.laboratorios = laboratorios;
	}



	public Integer getDiasDeInmunizacion() {
		return diasDeInmunizacion;
	}



	public void setDiasDeInmunizacion(Integer diasDeInmunizacion) {
		this.diasDeInmunizacion = diasDeInmunizacion;
	}



	public List<EspecificacionDosis> getDosis() {
		return dosis;
	}



	public void setDosis(List<EspecificacionDosis> dosis) {
		this.dosis = dosis;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}



	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}


	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		EspecificacionVacuna dat=(EspecificacionVacuna) arg0;
			
		return (this.id.compareTo(dat.getId())==0);
		}
	catch(Exception e)
		{
		return false;
		}
	}



	public String getLaboratoriosString() {
		List<String> lList=new ArrayList<String>();
		if(laboratorios!=null)
			for (Laboratorio o : laboratorios) {
				if(!lList.contains(o.getNombre()))
					lList.add(o.getNombre());
			}
		this.laboratoriosString=String.join(", ", lList);
		
		return laboratoriosString;
	}



	public void setLaboratoriosString(String laboratoriosString) {
		this.laboratoriosString = laboratoriosString;
	}

	
	
}