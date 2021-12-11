package uy.edu.fing.tse.vacunasuy.entity;


import java.io.Serializable;


import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Hora.findAll",query="Select c  from Hora C  order by  c.id DESC")
	
	})
@Entity
public class Hora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    String nombre;
    
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		Hora dat=(Hora) arg0;
			
		return (this.nombre.compareTo(dat.getNombre())==0);
		}
	catch(Exception e)
		{
		return false;
		}
	}
	
	
	
}