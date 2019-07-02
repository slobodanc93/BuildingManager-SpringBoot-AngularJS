package scvetkovic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Zgrada {
	
	@Column
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, unique=true)
	private String adresa;
	@Column(nullable=false)
	private String predsednik;
	@Column
	private Integer brojStanova;
	@Column(nullable=false)
	private Integer brojStanara;
	@OneToMany(mappedBy = "zgrada", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Poruka> poruke = new ArrayList<>();
	
	
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
	public List<Poruka> getPoruke() {
		return poruke;
	}
	public void setPoruke(List<Poruka> poruke) {
		this.poruke = poruke;
	}
	public void addPoruka(Poruka poruka) {
		this.poruke.add(poruka);
		if(!this.equals(poruka.getZgrada())) {
			poruka.setZgrada(this);
		}
	}
	
	

}
