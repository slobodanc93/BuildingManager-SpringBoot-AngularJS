package scvetkovic.web.dto;

public class ZgradaDTO {
	
	private Long id;
	private String adresa;
	private String predsednik;
	private Integer brojStanova;
	private Integer brojStanara;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getPredsednik() {
		return predsednik;
	}
	public void setPredsednik(String predsednik) {
		this.predsednik = predsednik;
	}
	public Integer getBrojStanova() {
		return brojStanova;
	}
	public void setBrojStanova(Integer brojStanova) {
		this.brojStanova = brojStanova;
	}
	public Integer getBrojStanara() {
		return brojStanara;
	}
	public void setBrojStanara(Integer brojStanara) {
		this.brojStanara = brojStanara;
	}

}
