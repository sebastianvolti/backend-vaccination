package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="LoteDosisSuministrada.findAll",query="Select c  from LoteDosis C  order by c.id ASC")
	})
@Entity
public class LoteDosis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String codigo;
	private Integer cant;
	private String estado;
	
	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name="LoteDosis_Persona", 
    joinColumns=@JoinColumn(name="lotedosis_id"),
    inverseJoinColumns=@JoinColumn(name="persona_id"))
	private List<Persona> vacunados;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	

	@ManyToOne(fetch=FetchType.LAZY)
	private Lote lote;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Etapa etapa;
	public LoteDosis() {
		
	}
	public LoteDosis(String codigo, Integer cantidad, Date fechaEvento, Lote l, String estado, Etapa e) {
		this.codigo=codigo;
		// TODO Auto-generated constructor stub
		cant=cantidad;
		fecha=fechaEvento;
		this.estado=estado;
		lote=l;
		etapa=e;
	}
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
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	public Etapa getEtapa() {
		return etapa;
	}
	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	public List<Persona> getVacunados() {
		return vacunados;
	}
	public void setVacunados(List<Persona> vacunados) {
		this.vacunados = vacunados;
	}
	
}