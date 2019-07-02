package scvetkovic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Glas {
	
	@Column
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String predlogPodrzan;
	@Column
	private String komentar;
	@ManyToOne(fetch = FetchType.EAGER)
	private Poruka poruka;
	
	
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
	public Poruka getPoruka() {
		return poruka;
	}
	public void setPoruka(Poruka poruka) {
		this.poruka = poruka;
		if(poruka != null & !poruka.getGlasovi().contains(this)) {
			poruka.getGlasovi().add(this);
		}
	}
	public String getKomentar() {
		return komentar;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	
	
	
}
