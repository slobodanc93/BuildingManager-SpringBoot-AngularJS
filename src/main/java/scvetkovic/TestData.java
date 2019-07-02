package scvetkovic;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import scvetkovic.model.Glas;
import scvetkovic.model.Poruka;
import scvetkovic.model.Zgrada;
import scvetkovic.service.GlasService;
import scvetkovic.service.PorukaService;
import scvetkovic.service.ZgradaService;


@Component
public class TestData {

	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private ZgradaService zgradaService;
	
	@Autowired
	private GlasService glasService;
	
	@PostConstruct
	public void init() {
		
		/*** ZGRADE ***/
		Zgrada z1 = new Zgrada();
		z1.setAdresa("Gogoljeva 1");
		z1.setBrojStanara(30);
		z1.setBrojStanova(10);
		z1.setPredsednik("Goran Goranovic");
		zgradaService.save(z1);
		
		Zgrada z2 = new Zgrada();
		z2.setAdresa("Blazakova 3");
		z2.setBrojStanara(50);
		z2.setBrojStanova(15);
		z2.setPredsednik("Branko Brankic");
		zgradaService.save(z2);
		
		Zgrada z3 = new Zgrada();
		z3.setAdresa("Bulevar Cara Lazara 5");
		z3.setBrojStanara(10);
		z3.setBrojStanova(2);
		z3.setPredsednik("Bojana Bojanic");
		zgradaService.save(z3);
		
		
		/*** PORUKE ***/
		Poruka p1 = new Poruka();
		p1.setNaslov("Nestanak vode");
		p1.setTip("Obavestenje");
		p1.setOpis("15.5 od 8h");
		p1.setZgrada(z1);
		porukaService.save(p1);
		
		Poruka p2 = new Poruka();
		p2.setNaslov("Zamena brave");
		p2.setTip("Obavestenje");
		p2.setOpis("16.5. dolazi bravar");
		p2.setZgrada(z2);
		porukaService.save(p2);
		
		Poruka p3 = new Poruka();
		p3.setNaslov("Obnova fasade");
		p3.setTip("Predlog");
		p3.setOpis("Da li ste za?");
		p3.setZgrada(z3);
		p3.setPotrebanProcenat(100.0);
		p3.setPrihvacen(false);
		porukaService.save(p3);
		
		Poruka p4 = new Poruka();
		p4.setNaslov("Obnova fasade");
		p4.setTip("Predlog");
		p4.setOpis("Da li ste za?");
		p4.setZgrada(z2);
		p4.setPotrebanProcenat(3.0);
		p4.setPrihvacen(false);
		porukaService.save(p4);
		
		Poruka p5 = new Poruka();
		p5.setNaslov("Finansije");
		p5.setTip("Obavestenje");
		p5.setOpis("Stanje na nuli");
		p5.setZgrada(z1);
		porukaService.save(p5);
		
		
		/*** GLASOVI ***/
		Glas g1 = new Glas();
		g1.setPoruka(p3);
		g1.setPredlogPodrzan("DA");
		g1.setKomentar("Saglasan sam");
		glasService.save(g1);
		
		Glas g2 = new Glas();
		g2.setPoruka(p3);
		g2.setPredlogPodrzan("NE");
		g2.setKomentar("Nisam saglasan");
		glasService.save(g2);
		
		
	}
}