package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Etapa.findAll",query="Select c  from Etapa C  order by c.id DESC")
	})
@Entity
public class Etapa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    private Integer cupo;
	private String nombre;
    private Integer cupoRestante;
	@ManyToOne(fetch=FetchType.LAZY)
	private Plan plan;
	
	private Integer edadMinima;
	private Integer edadMaxima;
	private String objetivoNombre;
	
	private ArrayList<String> publicoObjetivo = new ArrayList<String>();

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fin;
	
	
	@OneToMany(mappedBy="etapa",fetch=FetchType.LAZY)
	private List<Agenda> agendas = new ArrayList<Agenda>();
	@OneToMany(mappedBy="etapa",fetch=FetchType.LAZY)
	private List<LoteDosis> loteDosis = new ArrayList<LoteDosis>();

	
	@ManyToOne(fetch=FetchType.LAZY)
	private EspecificacionVacuna vacuna;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public EspecificacionVacuna getVacuna() {
		return vacuna;
	}
	public void setVacuna(EspecificacionVacuna vacuna) {
		this.vacuna = vacuna;
	}
	public Integer getCupoRestante() {
		return cupoRestante;
	}
	public void setCupoRestante(Integer cupoRestante) {
		this.cupoRestante = cupoRestante;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public Integer getCupo() {
		return cupo;
	}
	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}
	


	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		Etapa dat=(Etapa) arg0;
			
		return (this.inicio.compareTo(dat.getInicio())==0 && this.fin.compareTo(dat.getFin())==0 && this.vacuna.getId().compareTo(dat.getVacuna().getId())==0 );
		}
	catch(Exception e)
		{
		return false;
		}
	}
	public List<Agenda> getAgendas() {
		return agendas;
	}
	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}
	public Integer getEdadMinima() {
		return edadMinima;
	}
	public void setEdadMinima(Integer edadMinima) {
		this.edadMinima = edadMinima;
	}
	public Integer getEdadMaxima() {
		return edadMaxima;
	}
	public void setEdadMaxima(Integer edadMaxima) {
		this.edadMaxima = edadMaxima;
	}
	public String getObjetivoNombre() {
		return objetivoNombre;
	}
	public void setObjetivoNombre(String objetivoNombre) {
		this.objetivoNombre = objetivoNombre;
	}
	public ArrayList<String> getPublicoObjetivo() {
		return publicoObjetivo;
	}
	public void setPublicoObjetivo(ArrayList<String> publicoObjetivo) {
		this.publicoObjetivo = publicoObjetivo;
	}
	public List<LoteDosis> getLoteDosis() {
		return loteDosis;
	}
	public void setLoteDosis(List<LoteDosis> loteDosis) {
		this.loteDosis = loteDosis;
	}
	

	
}