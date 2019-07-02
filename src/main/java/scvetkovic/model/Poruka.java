package scvetkovic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Poruka {
	
	@Column
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private String naslov;
	@Column
	private String tip;
	@Column
	private Double potrebanProcenat;
	@Column
	private Boolean prihvacen;
	@Column
	private String opis;
	@ManyToOne(fetch = FetchType.EAGER)
	private Zgrada zgrada;
	@OneToMany(mappedBy = "poruka", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Glas> glasovi = new ArrayList<>();
	
	
	
	public Poruka() {
		prihvacen = false;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public Double getPotrebanProcenat() {
		return potrebanProcenat;
	}
	public void setPotrebanProcenat(Double potrebanProcenat) {
		this.potrebanProcenat = potrebanProcenat;
	}
	public Boolean getPrihvacen() {
		return prihvacen;
	}
	public void setPrihvacen(Boolean prihvacen) {
		this.prihvacen = prihvacen;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Zgrada getZgrada() {
		return zgrada;
	}
	public void setZgrada(Zgrada zgrada) {
		this.zgrada = zgrada;
		if(zgrada != null & !zgrada.getPoruke().contains(this)) {
			zgrada.getPoruke().add(this);
		}
	}
	public List<Glas> getGlasovi() {
		return glasovi;
	}
	public void setGlasovi(List<Glas> glasovi) {
		this.glasovi = glasovi;
	}
	public void addGlas(Glas glas) {
		this.glasovi.add(glas);
		if(!this.equals(glas.getPoruka())) {
			glas.setPoruka(this);
		}
	}
	

}
