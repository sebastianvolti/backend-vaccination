package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Persona.findAll",query="Select c  from Persona C  order by c.id ASC"),
	@NamedQuery(name="Persona.findAllBackOffice",query="Select distinct c  from Persona C  inner join c.roles r  where  (r.nombre ='Administrador' or r.nombre ='Autoridad' or r.nombre ='Vacunador') order by c.id ASC"),
@NamedQuery(name="Persona.findAllByCi",query="Select c  from Persona C where c.ci=:ci  order by c.id ASC"),
@NamedQuery(name="Persona.findVacunadorByCi",query="Select distinct c  from Persona C inner join c.roles r  where  (r.nombre ='Vacunador' ) and c.ci=:ci  order by c.id ASC"),

@NamedQuery(name="Persona.findCiudadanoByCi",query="Select distinct c  from Persona C inner join c.roles r  where  r.nombre ='Ciudadano' and  c.ci=:ci  order by c.id ASC"),
@NamedQuery(name="Persona.findBackByCi",query="Select distinct c  from Persona C inner join c.roles r where c.ci=:ci  and (r.nombre ='Administrador' or r.nombre ='Autoridad')  order by c.id ASC"),

	})
@Entity
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true)
	private String ci;
	private String email;
	private String celular;
	private String nombre;
	private String apellido;
	@Column(columnDefinition = "text")
	private String hash;
	@Column(columnDefinition = "text")
	private String salt;
	@Transient
	private String rolesString;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	
	@Transient
	private Boolean resetPassword=false;

    @ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Persona_Rol", 
    joinColumns=@JoinColumn(name="persona_id"),
    inverseJoinColumns=@JoinColumn(name="rol_id"))
	private List<Rol> roles=new ArrayList<Rol>();
	
	@OneToMany(mappedBy="puestoTrabajoAsignado",fetch=FetchType.LAZY,  orphanRemoval = true, cascade = CascadeType.ALL)
	private List<VacunadorPuestoAsignado> puestosAsignados= new ArrayList<VacunadorPuestoAsignado>();;
	
	
	@OneToMany(mappedBy="persona",fetch=FetchType.LAZY,  orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Solicitud> solicitudes= new ArrayList<Solicitud>();
	
	@ManyToMany(mappedBy="vacunados",fetch=FetchType.LAZY)
	private List<LoteDosis> dosisRecibidas= new ArrayList<LoteDosis>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		this.ci = ci;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public List<Rol> getRoles() {
		return roles;
	}
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<VacunadorPuestoAsignado> getPuestosAsignados() {
		return puestosAsignados;
	}
	public void setPuestosAsignados(List<VacunadorPuestoAsignado> puestosAsignados) {
		this.puestosAsignados = puestosAsignados;
	}
	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getRolesString() {
		List<String> rolList=new ArrayList<String>();
		if(roles!=null)
			for (Rol o : roles) {
				if(!rolList.contains(o.getNombre()))
					rolList.add(o.getNombre());
			}
		this.rolesString=String.join(", ", rolList);
		
		return rolesString;
	}
	public void setRolesString(String rolesString) {
		this.rolesString = rolesString;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Boolean getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(Boolean resetPassword) {
		this.resetPassword = resetPassword;
	}
	public List<LoteDosis> getDosisRecibidas() {
		return dosisRecibidas;
	}
	public void setDosisRecibidas(List<LoteDosis> dosisRecibidas) {
		this.dosisRecibidas = dosisRecibidas;
	}
	

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		Persona dat=(Persona) arg0;
			
		return (this.ci.equals(dat.getCi()));
		}
	catch(Exception e)
		{
		return false;
		}
	}

}