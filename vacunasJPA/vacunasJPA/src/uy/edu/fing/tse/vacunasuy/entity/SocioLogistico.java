package uy.edu.fing.tse.vacunasuy.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;




@NamedQueries({	@NamedQuery(name="SocioLogistico.findAll",query="Select c  from SocioLogistico C  order by c.id ASC"),
	@NamedQuery(name="SocioLogistico.findByCodigo",query="Select c  from SocioLogistico C  where c.codigo=:codigo order by c.id ASC"),
	@NamedQuery(name="SocioLogistico.findByToken",query="Select c  from SocioLogistico C  where c.token=:token"),
	
	})
@Entity
public class SocioLogistico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(unique=true)
	private String codigo;
	
	@Column(unique=true)
	private String token;
	
	@OneToMany(mappedBy="socioLogistico",fetch=FetchType.LAZY, orphanRemoval = true)
	private List<Lote> lotes=  new ArrayList<Lote>();
	
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
	public List<Lote> getLotes() {
		return lotes;
	}
	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
	try
	{
		SocioLogistico dat=(SocioLogistico) arg0;
			
		return (this.codigo.equals(dat.getCodigo()));
		}
	catch(Exception e)
		{
		return false;
		}
	}

}