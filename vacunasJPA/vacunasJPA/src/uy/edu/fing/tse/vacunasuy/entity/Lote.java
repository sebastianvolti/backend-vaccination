package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="Lote.findAll",query="Select c  from Lote C  order by c.id ASC"),
	@NamedQuery(name="Lote.findByCodigoByVacunatorioID",query="Select c  from Lote C where c.codigo=:codigo and c.vacunatorio.id=:vacunatorioId order by c.id ASC"),
	@NamedQuery(name="Lote.findPendienteBySocioLogisticoId",query="Select c  from Lote C where c.estado='Pendiente' and c.socioLogistico.id=:socioLogisticoId order by c.vacunatorio.id  ASC"),
	@NamedQuery(name="Lote.findTransitoBySocioLogisticoId",query="Select c  from Lote C where c.estado='Transito' and c.socioLogistico.id=:socioLogisticoId order by c.vacunatorio.id  ASC"),
	@NamedQuery(name="Lote.findEntregadoBySocioLogisticoId",query="Select c  from Lote C where c.estado='Entregado' and c.socioLogistico.id=:socioLogisticoId order by c.vacunatorio.id  ASC")

	})
@Entity
public class Lote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    @Column(unique=true)
	private String codigo;
	private Integer cant;
	private String estado="Pendiente";

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha=new Date();
	

	@ManyToOne(fetch=FetchType.LAZY)
	private Vacunatorio vacunatorio;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private SocioLogistico socioLogistico;
	
	@OneToMany(mappedBy="lote",fetch=FetchType.LAZY,  orphanRemoval = true, cascade = CascadeType.ALL)
	private List<LoteDosis> dosis = new ArrayList<LoteDosis>();
	
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}
	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}
	public Integer getCant() {
		return cant;
	}
	public void setCant(Integer cant) {
		this.cant = cant;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public SocioLogistico getSocioLogistico() {
		return socioLogistico;
	}
	public void setSocioLogistico(SocioLogistico socioLogistico) {
		this.socioLogistico = socioLogistico;
	}
	public List<LoteDosis> getDosis() {
		return dosis;
	}
	public void setDosis(List<LoteDosis> dosis) {
		this.dosis = dosis;
	}
	
}