package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="DiaHoraLugar.findAll",query="Select c  from DiaHoraLugar C  order by   c.id DESC"),
@NamedQuery(name="DiaHoraLugar.findAllByVacunatorioIdByFecha",query="Select distinct c  from DiaHoraLugar C join c.diaHoraEspecifico dhe where dhe.diaHoraEspecifico=:fecha and c.vacunatorio.id=:vacunatorioId order by   c.id DESC")

	
	})
@Entity
public class DiaHoraLugar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    

	@OneToOne(fetch=FetchType.LAZY)
	private Agenda agenda;
	@OneToMany(fetch=FetchType.LAZY,mappedBy ="diaHoraLugar" ,cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Dia> dias= new ArrayList<Dia>();
	@OneToMany(fetch=FetchType.LAZY,mappedBy ="diaHoraLugar" ,cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Hora> horas= new ArrayList<Hora>();
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy ="diaHoraLugar" ,cascade=CascadeType.ALL, orphanRemoval=true)
	private List<DiaHora> diaHoraEspecifico= new ArrayList<DiaHora>();
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Vacunatorio vacunatorio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<Dia> getDias() {
		return dias;
	}
	public void setDias(List<Dia> dias) {
		this.dias = dias;
	}
	public List<Hora> getHoras() {
		return horas;
	}
	public void setHoras(List<Hora> horas) {
		this.horas = horas;
	}
	public Vacunatorio getVacunatorio() {
		return vacunatorio;
	}
	public void setVacunatorio(Vacunatorio vacunatorio) {
		this.vacunatorio = vacunatorio;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	public List<DiaHora> getDiaHoraEspecifico() {
		return diaHoraEspecifico;
	}
	public void setDiaHoraEspecifico(List<DiaHora> diaHoraEspecifico) {
		this.diaHoraEspecifico = diaHoraEspecifico;
	}
	
	
	
	
}