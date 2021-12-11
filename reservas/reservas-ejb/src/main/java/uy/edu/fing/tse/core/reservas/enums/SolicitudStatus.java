package uy.edu.fing.tse.core.reservas.enums;

public enum SolicitudStatus {
	PENDING, //cuando se ingresa la solicitud pero no hay cupos para generar una reserva.
	ASSIGNED, //cuando se crearon las reservas para las dosis pero aún no se vacunó por compelto.
	DONE; //cuando el ciudadno se dió todas las dosis.

	public static SolicitudStatus findByString(String status) {
		for(SolicitudStatus s : SolicitudStatus.values()) {
			if(s.name().equalsIgnoreCase(status)) {
				return s;
			}
		}

		return null;
	}
}
