package scvetkovic.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import scvetkovic.model.Glas;
import scvetkovic.model.Poruka;
import scvetkovic.service.GlasService;
import scvetkovic.service.PorukaService;
import scvetkovic.support.GlasDTOToGlas;
import scvetkovic.support.GlasToGlasDTO;
import scvetkovic.support.PorukaDTOToPoruka;
import scvetkovic.support.PorukaToPorukaDTO;
import scvetkovic.web.dto.GlasDTO;
import scvetkovic.web.dto.PorukaDTO;

@RestController
@RequestMapping("/api/poruke")
public class ApiPorukaController {
	
	
	/*** SERVICE & OBJECT TRANSFORMATION LAYER THROUGH DEPENDENCY INJECTION ***/
	@Autowired
	private PorukaToPorukaDTO toPorukaDTO;

	@Autowired
	private PorukaDTOToPoruka toPoruka;

	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private GlasService glasService;
	
	@Autowired
	private GlasToGlasDTO toGlasDTO;
	
	@Autowired
	private GlasDTOToGlas toGlas;
	
	
	
	/*** GET ALL WITH OPTIONAL SEARCH PARAMETERS ***/
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PorukaDTO>> get(
			@RequestParam(required = false) Long zgradaId,
			@RequestParam(required = false) String naslov,
			@RequestParam(required = false) String tip,
			@RequestParam(defaultValue = "0") int pageNum) {

		Page<Poruka> poruke;

		if (zgradaId != null || naslov != null || tip != null) {
			poruke = porukaService.pretraga(zgradaId, naslov, tip, pageNum);
		} else {
			poruke = porukaService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(poruke.getTotalPages()));

		return new ResponseEntity<>(
				toPorukaDTO.convert(poruke.getContent()), 
				headers, 
				HttpStatus.OK);
	}
	
	
	/*** GET ONE ***/
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PorukaDTO> get(@PathVariable Long id) {

		Poruka poruka = porukaService.findOne(id);

		if (poruka == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(
				toPorukaDTO.convert(poruka), 
				HttpStatus.OK);
	}
	
	
	/*** POST ***/
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<PorukaDTO> add(
			@Validated @RequestBody PorukaDTO novaPoruka) {

		Poruka poruka = toPoruka.convert(novaPoruka);
		porukaService.save(poruka);

		return new ResponseEntity<>(
				toPorukaDTO.convert(poruka), 
				HttpStatus.CREATED);
	}
	

	@RequestMapping(method=RequestMethod.POST, value="/glasanje")
	public ResponseEntity<GlasDTO> glasaj(
			@RequestBody GlasDTO novGlas){
		
		Glas glas = toGlas.convert(novGlas);
		glasService.glasaj(glas);
		
		return new ResponseEntity<>(
				toGlasDTO.convert(glas), 
				HttpStatus.CREATED);
	}
	
	
	
	/*** PUT ***/
	@RequestMapping(method=RequestMethod.PUT, value="/{id}", consumes="application/json")
	public ResponseEntity<PorukaDTO> edit(
			@PathVariable Long id,
			@Validated @RequestBody PorukaDTO izmenjena){
		
		if(!id.equals(izmenjena.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Poruka poruka = toPoruka.convert(izmenjena); 
		porukaService.save(poruka);
		
		return new ResponseEntity<>(
				toPorukaDTO.convert(poruka), 
				HttpStatus.OK);
	}
	
	/*** DELETE ***/
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<PorukaDTO> delete(
			@PathVariable Long id){
		
		Poruka deleted = porukaService.remove(id);
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toPorukaDTO.convert(deleted),
				HttpStatus.OK);
	}
	
	
	/*** EXCEPTION HANDLER ***/
	@ExceptionHandler(value= {DataIntegrityViolationException.class, IllegalArgumentException.class})
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	

}
