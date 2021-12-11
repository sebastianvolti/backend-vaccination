package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Plan.findAll",query="Select c  from Plan C  order by c.id DESC")
	})
@Entity
public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    
	private String nombre;

	@ManyToMany(fetch=FetchType.LAZY)
	private List<Enfermedad> enfermedades = new ArrayList<Enfermedad>();
	

	@OneToMany(mappedBy="plan",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Etapa> etapas = new ArrayList<Etapa>();
	@Transient
	private String enfermedadesString;
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
	public List<Enfermedad> getEnfermedades() {
		return enfermedades;
	}
	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}
	public List<Etapa> getEtapas() {
		return etapas;
	}
	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}
	public String getEnfermedadesString() {
		List<String> enfList=new ArrayList<String>();
		if(enfermedades!=null)
			for (Enfermedad enf : enfermedades) {
				if(!enfList.contains(enf.getNombre()))
				enfList.add(enf.getNombre());
			}
		this.enfermedadesString=String.join(", ", enfList);
		return enfermedadesString;
	}
	public void setEnfermedadesString(String enfermedadesString) {
		this.enfermedadesString = enfermedadesString;
	}
	
	
}