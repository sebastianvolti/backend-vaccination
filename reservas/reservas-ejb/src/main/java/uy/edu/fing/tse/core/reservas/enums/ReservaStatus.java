package uy.edu.fing.tse.core.reservas.enums;

public enum ReservaStatus {
	PENDING, //cuando el ciudadano aún no se dio la dosis asociada a esta reserva
	SKIPPED, //cuando el ciudadano no se presenta a darse la dosis
	DONE //cuando se dio la dosis asociada a esta reserva
}
