package uy.edu.fing.tse.vacunasuy.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Laboratorio.findAll",query="Select c  from Laboratorio C  order by  c.id DESC")
	
	})
@Entity
public class Laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    String nombre;
    

    @ManyToMany(mappedBy="laboratorios",fetch=FetchType.LAZY)
	private List<EspecificacionVacuna> vacunas=  new ArrayList<EspecificacionVacuna>();
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
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		Laboratorio dat=(Laboratorio) arg0;
			
		return (this.nombre.equals(dat.getNombre()));
		}
	catch(Exception e)
		{
		return false;
		}
	}
	public List<EspecificacionVacuna> getVacunas() {
		return vacunas;
	}
	public void setVacunas(List<EspecificacionVacuna> vacunas) {
		this.vacunas = vacunas;
	}
	
	
	
}