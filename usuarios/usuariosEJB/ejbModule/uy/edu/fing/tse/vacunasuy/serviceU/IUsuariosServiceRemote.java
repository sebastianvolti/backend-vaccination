package uy.edu.fing.tse.vacunasuy.serviceU;

import java.util.List;

import javax.ejb.Remote;

import uy.edu.fing.tse.vacunasuy.entity.Persona;
import uy.edu.fing.tse.vacunasuy.entity.Rol;


public interface IUsuariosServiceRemote {
	Persona getUserByNameByPassword(String user, String name);

	List<Persona> getUsuariosAllBackOffice();
	List<Rol> getRolesAll();
	List<Rol> getRolesBackAll();
	Persona getUsuarioLazyById(Integer id);
	Persona getUsuarioLazyByCi(String ci);
	InternalUserEntity getCiudadano(String ci);
	void nuevoCiudadano(InternalUserEntity per);
	void eliminarUsuario(Integer id);
	Persona guardarUsuario(Persona usuarioSeleccionado);
	Boolean isUsuarioVacunador(String ci);
	Persona getUsuarioBackByCiByPassword(String nombreUsuario, String claveUsuario);

	List<Persona> getUsuariosAllVacunador();
	
}
