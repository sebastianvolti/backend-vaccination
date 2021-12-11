package uy.edu.fing.tse.core.reservas.rest.entities;

public class VacunatorioEntity {

	private Integer id;

	private String nombre;

	private String departamento;



	public VacunatorioEntity(Integer id, String nombre, String departamento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
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

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


}
