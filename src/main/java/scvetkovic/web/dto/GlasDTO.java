package scvetkovic.web.dto;

public class GlasDTO {
	
	private Long id;
	private String predlogPodrzan;
	private Long porukaId;
	private String porukaNaslov;
	private String komentar;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPredlogPodrzan() {
		return predlogPodrzan;
	}
	public void setPredlogPodrzan(String predlogPodrzan) {
		this.predlogPodrzan = predlogPodrzan;
	}
	public Long getPorukaId() {
		return porukaId;
	}
	public void setPorukaId(Long porukaId) {
		this.porukaId = porukaId;
	}
	public String getPorukaNaslov() {
		return porukaNaslov;
	}
	public void setPorukaNaslov(String porukaNaslov) {
		this.porukaNaslov = porukaNaslov;
	}
	public String getKomentar() {
		return komentar;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	

}
