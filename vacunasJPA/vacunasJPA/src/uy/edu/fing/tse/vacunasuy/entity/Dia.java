package uy.edu.fing.tse.vacunasuy.entity;


import java.io.Serializable;


import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Dia.findAll",query="Select c  from Dia C  order by  c.id DESC")
	
	})
@Entity
public class Dia implements Serializable {
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
		Dia dat=(Dia) arg0;
			
		return (this.nombre.equals(dat.getNombre()));
		}
	catch(Exception e)
		{
		return false;
		}
	}
	
	
	
}