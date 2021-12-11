package core.reports.entities;
import java.io.Serializable;


public class AgendaPublicEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5019097375960424437L;
	private String code;
	private String depto;
	private String vaccination;
	private Long quotas;
	private String ageRange;
	private String doses;

	public AgendaPublicEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public AgendaPublicEntity(String code, String depto, String vaccination, Long quotas, String ageRange, String doses) {
		super();
		this.code = code;
		this.depto = depto;
		this.vaccination = vaccination;
		this.quotas = quotas;
		this.ageRange = ageRange;
		this.doses = doses;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getVaccination() {
		return vaccination;
	}

	public void setVaccination(String vaccination) {
		this.vaccination = vaccination;
	}

	public Long getQuotas() {
		return quotas;
	}

	public void setQuotas(Long quotas) {
		this.quotas = quotas;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public String getDoses() {
		return doses;
	}

	public void setDoses(String doses) {
		this.doses = doses;
	}


	
}
