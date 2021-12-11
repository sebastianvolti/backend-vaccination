package uy.edu.fing.tse.core.reservas.enums;

public enum Departamento {
	ARTIGAS,
	CANELONES,
	CERRO_LARGO,
	COLONIA,
	DURAZNO,
	FLORES,
	FLORIDA,
	LAVALLEJA,
	MALDONADO,
	MONTEVIDEO,
	PAYSANDU,
	RIO_NEGRO,
	RIVERA,
	ROCHA,
	SALTO,
	SAN_JOSE,
	SORIANO,
	TACUAREMBO,
	TREINTA_Y_TRES;

	public Departamento fromString(String depto) {
		for(Departamento d : Departamento.values()) {
			if(depto.equalsIgnoreCase(d.name())) {
				return d;
			}
		}

		return null;
	}
}
