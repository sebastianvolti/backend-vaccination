package uy.edu.fing.tse.core.reservas.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application{

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public RestApplication() {
		this.classes.add(SolicitudRestService.class);
		this.classes.add(ReservasRestService.class);
		this.classes.add(BackofficeRestService.class);
	}

	@Override
	public Set<Object> getSingletons() {
		return this.singletons;
	}

	@Override
	public Set<Class<?>> getClasses(){
		return this.classes;
	}
}
