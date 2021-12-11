package uy.edu.fing.tse.vacunasuy.serviceU.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tse.vacunasuy.entity.Enfermedad;
import uy.edu.fing.tse.vacunasuy.entity.LoteDosis;
import uy.edu.fing.tse.vacunasuy.entity.Persona;
import uy.edu.fing.tse.vacunasuy.entity.Rol;
import uy.edu.fing.tse.vacunasuy.serviceU.IUsuariosServiceRemote;
import uy.edu.fing.tse.vacunasuy.serviceU.InternalUserEntity;
import uy.edu.fing.tse.vacunasuy.utils.MyHashSalt;

@Stateless
@Remote (IUsuariosServiceRemote.class)
public class UsuariosServiceRemote implements IUsuariosServiceRemote {
	@PersistenceContext(unitName = "vacunasuyJPA")
	private EntityManager em;
	@Override
	public Persona getUserByNameByPassword(String user, String name) {
		// TODO Auto-generated method stub
		return new Persona();
	}

	
	@Override
	public List<Persona> getUsuariosAllBackOffice() {
		// TODO Auto-generated method stub
		List<Persona> objList=null;
	Query query= em.createNamedQuery("Persona.findAllBackOffice");
	objList= query.getResultList();
	for (Persona enf : objList) {
		if(enf.getRoles()!=null)
			enf.getRoles().size();
	}
	
		return objList;
	}
	
	@Override
	public List<Persona> getUsuariosAllVacunador() {
		// TODO Auto-generated method stub
		List<Persona> objList=null;
		List<Persona> objListResult=new ArrayList<Persona>();
	Query query= em.createNamedQuery("Persona.findAllBackOffice");
	objList= query.getResultList();
	for (Persona enf : objList) {
		if(enf.getRoles()!=null)
			for (Rol r : enf.getRoles()) {
				if(r.getNombre().equals("Vacunador") && !objListResult.contains(enf))
					objListResult.add(enf);
			}
			
	}
	
		return objListResult;
	}
	@Override
	public Boolean isUsuarioVacunador(String ci) {
		// TODO Auto-generated method stub
		List<Persona> objList=null;
	Query query= em.createNamedQuery("Persona.findVacunadorByCi");
	query.setParameter("ci",ci);
	objList= query.getResultList();
	for (Persona enf : objList) {
				return true;
	}
	
		return false;
	}


	@Override
	public List<Rol> getRolesAll() {
		// TODO Auto-generated method stub
		List<Rol> objList=null;
		Query query= em.createNamedQuery("Rol.findAll");
		objList= query.getResultList();
		if(objList!=null)
			for (Rol rol : objList) {
				if(rol.getPersonas()!=null)
					rol.getPersonas().size();
			}
		
			return objList;
	}

	@Override
	public List<Rol> getRolesBackAll() {
		// TODO Auto-generated method stub
		List<Rol> objList=null;
		Query query= em.createNamedQuery("Rol.findBackAll");
		objList= query.getResultList();
		if(objList!=null)
			for (Rol rol : objList) {
				if(rol.getPersonas()!=null)
					rol.getPersonas().size();
			}
		
			return objList;
	}
	@Override
	public Persona getUsuarioLazyById(Integer id) {
		// TODO Auto-generated method stub
		Persona p=em.find(Persona.class, id);
		
		if(p.getRoles()!=null)
			for (Rol rol : p.getRoles()) {
				rol.getNombre();
			}
		
		return  p;
	}


	@Override
	public void eliminarUsuario(Integer id) {
		// TODO Auto-generated method stub
		Persona p=em.find(Persona.class, id);
		if(p!=null)
			em.remove(p);
	}


	@Override
	public Persona guardarUsuario(Persona usuarioSeleccionado) {
		// TODO Auto-generated method stub
		
		MyHashSalt mh= new MyHashSalt();
		if(usuarioSeleccionado.getSalt()==null || usuarioSeleccionado.getSalt().equals("") || usuarioSeleccionado.getResetPassword())
			usuarioSeleccionado.setSalt( new String(mh.getSalt(), StandardCharsets.UTF_8));
		if(usuarioSeleccionado.getHash()==null || usuarioSeleccionado.getHash().equals("") || usuarioSeleccionado.getResetPassword())
		{
			String password=mh.generateRandomPasswordPlain();
			usuarioSeleccionado.setHash(mh.cifrar(password, usuarioSeleccionado.getSalt().getBytes()));
			
		}
		
		Persona p=em.merge(usuarioSeleccionado);
		return getUsuarioLazyById(p.getId());
	}


	@Override
	public Persona getUsuarioBackByCiByPassword(String nombreUsuario, String claveUsuario) {
		// TODO Auto-generated method stub
		Query query= em.createNamedQuery("Persona.findBackByCi");
		query.setParameter("ci",nombreUsuario);
		
		MyHashSalt myhash= new MyHashSalt();
		
		try
		{
			Persona p= (Persona) query.getSingleResult();
			if(p==null)
				return null;
			if(p.getHash()==null ||p.getHash().equals("") || p.getSalt()==null ||p.getSalt().equals(""))
				return null;
			
			String passcif=myhash.cifrar(claveUsuario,p.getSalt().getBytes());
			if(!p.getHash().equals(passcif))
				return null;
			if(p.getRoles()!=null)
				p.getRoles().size();
			
			return p;
		}
		catch ( Exception e) {
			return null;
		}
		
	}

	public Persona getCiudadanoPersona(String ci) {
		// TODO Auto-generated method stub
		Query query= em.createNamedQuery("Persona.findCiudadanoByCi");
		query.setParameter("ci",ci);
		try
		{
			Persona p= (Persona) query.getSingleResult();
			return p;
		}
		catch ( Exception e) {
			return null;
		}
		
	}
	@Override
	public InternalUserEntity getCiudadano(String ci) {
		// TODO Auto-generated method stub
		Persona pp=getCiudadanoPersona(ci);
		if(pp==null)
			return null;
		else
		{
			InternalUserEntity p=new InternalUserEntity(pp.getCi(), pp.getNombre(), pp.getApellido(), pp.getEmail(),""); 
			return p;
		}
		
	}


	@Override
	public void nuevoCiudadano(InternalUserEntity iuE) {
		Persona p=getCiudadanoPersona(iuE.getUserId());
		if(p==null)
		{
			p=new Persona();
			p.setApellido(iuE.getUserLastName());
			p.setNombre(iuE.getUserName());
			p.setCi(iuE.getUserId());
			p.setEmail(iuE.getUserEmail());
		}
		List<Rol> roles=getRolesAll();
		Rol rolCiudadano=null;
		for (Rol rol : roles) {
			if(rol.getNombre().equals("Ciudadano"))
				rolCiudadano=rol;
		}
		if(p.getRoles()==null)
			p.setRoles(new ArrayList<Rol>());
		if(!p.getRoles().contains(rolCiudadano))
			p.getRoles().add(rolCiudadano);
		
		em.merge(p);

	}


	@Override
	public Persona getUsuarioLazyByCi(String ci) {
		// TODO Auto-generated method stub
		Query query= em.createNamedQuery("Persona.findAllByCi");
		query.setParameter("ci",ci);
		query.setMaxResults(1);
		Persona p=null;
		try
		{
			List<Persona> pl=query.getResultList();
			if(pl!=null && pl.size()>0)
			{
				p= pl.get(0); 
			if(p.getRoles()!=null)
				for (Rol rol : p.getRoles()) {
					rol.getNombre();
				}
				if(p.getDosisRecibidas()!=null)
					for (LoteDosis ld : p.getDosisRecibidas()) {
						if(ld.getEtapa()!=null)
						{
							ld.getEtapa().getNombre();
							ld.getEtapa().getVacuna().getDiasDeInmunizacion();
							ld.getEtapa().getPlan().getNombre();
							if(ld.getEtapa().getPlan().getEnfermedades()!=null)
								for (Enfermedad e : ld.getEtapa().getPlan().getEnfermedades()) {
									e.getNombre();
								}
							
							ld.getLote().getVacunatorio().getNombre();
						}
					}
			}
			
		}
		catch ( Exception e) {
			return null;
		}
		
		return  p;
	}
	
}
