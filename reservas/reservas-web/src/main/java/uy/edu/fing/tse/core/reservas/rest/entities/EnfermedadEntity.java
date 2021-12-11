package uy.edu.fing.tse.core.reservas.rest.entities;

public class EnfermedadEntity {
	private Integer id;
	private String nombre;


	public EnfermedadEntity(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return this.nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


}
