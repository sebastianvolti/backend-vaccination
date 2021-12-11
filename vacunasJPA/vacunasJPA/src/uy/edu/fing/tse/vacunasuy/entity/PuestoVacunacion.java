package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="PuestoVacunacion.findAll",query="Select c  from PuestoVacunacion C  order by c.id ASC"),
@NamedQuery(name="PuestoVacunacion.findAllByVac",query="Select distinct  c  from PuestoVacunacion C  join c.vacunadoresAsignados vpa join vpa.vacunadorAsignado va where c.vacunatorio.id=:idVacunatorio order  by vpa.fecha desc, c.codigo, va.ci"),
@NamedQuery(name="PuestoVacunacion.findAllByVacByDate",query="Select distinct c   from PuestoVacunacion C  join c.vacunadoresAsignados vpa join vpa.vacunadorAsignado va where c.vacunatorio.id=:idVacunatorio and vpa.fecha=:fecha  order by c.codigo, va.ci"),
@NamedQuery(name="PuestoVacunacion.findByPuestoCodigoByIdVacunatorio",query="Select distinct c   from PuestoVacunacion C   where c.vacunatorio.id=:vacunatorioId and c.codigo=:puestoCodigo")


	})
@Entity
public class PuestoVacunacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	

	@OneToMany(mappedBy="puestoTrabajoAsignado",fetch=FetchType.LAZY,  orphanRemoval = true, cascade = CascadeType.ALL)
	private List<VacunadorPuestoAsignado> vacunadoresAsignados= new ArrayList<VacunadorPuestoAsignado>();
	

	@ManyToOne(fetch=FetchType.LAZY)
	private Vacunatorio vacunatorio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<VacunadorPuestoAsignado> getVacunadoresAsignados() {
		return vacunadoresAsignados;
	}
	public void setVacunadoresAsignados(List<VacunadorPuestoAsignado> vacunadoresAsignados) {
		this.vacunadoresAsignados = vacunadoresAsignados;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}
	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}
	

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		PuestoVacunacion dat=(PuestoVacunacion) arg0;
			
		return (this.id.equals(dat.getId()));
		}
	catch(Exception e)
		{
		return false;
		}
	}

}