package uy.edu.fing.tse.core.reservas.wrappers;

import java.util.List;

import uy.edu.fing.tse.vacunasuy.entity.Enfermedad;

public class EnfermedadesWrapper {
	private List<Enfermedad> enfermedades;

	public EnfermedadesWrapper(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}

	public List<Enfermedad> getEnfermedades() {
		return this.enfermedades;
	}

	public void setEnfermedades(List<Enfermedad> enfermedades) {
		this.enfermedades = enfermedades;
	}
}
