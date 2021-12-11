package uy.edu.fing.tse.vacunasuy.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Rol.findAll",query="Select c  from Rol C  order by  c.id DESC"),
	@NamedQuery(name="Rol.findBackAll",query="Select c  from Rol C where not c.nombre='Ciudadano' order by  c.id DESC")

	})
@Entity
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    String nombre;
    
    
    @ManyToMany(mappedBy="roles",fetch=FetchType.LAZY)
	private List<Persona> personas=  new ArrayList<Persona>();
    
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
		Rol dat=(Rol) arg0;
			
		return (this.nombre.equals(dat.getNombre()));
		}
	catch(Exception e)
		{
		return false;
		}
	}
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	
	
	
}