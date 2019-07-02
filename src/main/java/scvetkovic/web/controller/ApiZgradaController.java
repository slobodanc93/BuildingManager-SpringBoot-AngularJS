package scvetkovic.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import scvetkovic.model.Poruka;
import scvetkovic.service.PorukaService;
import scvetkovic.service.ZgradaService;
import scvetkovic.support.PorukaToPorukaDTO;
import scvetkovic.support.ZgradaToZgradaDTO;
import scvetkovic.web.dto.PorukaDTO;
import scvetkovic.web.dto.ZgradaDTO;

@RestController
@RequestMapping("/api/zgrade")
public class ApiZgradaController {
	
	/*** SERVICE & OBJECT TRANSFORMATION LAYER THROUGH DEPENDENCY INJECTION ***/
	@Autowired
	private ZgradaService zgradaService;
	
	@Autowired
	private ZgradaToZgradaDTO toZgradaDTO;
	
	@Autowired
	private PorukaService porukaService;
	
	@Autowired
	private PorukaToPorukaDTO toPorukaDTO;
	
	
	/*** GET ALL  ***/
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ZgradaDTO>> get(){
		return new ResponseEntity<>(
				toZgradaDTO.convert(zgradaService.findAll()),
				HttpStatus.OK);
	}
	
	/*** SVE PORUKE OD ZGRADE ***/
	@RequestMapping(method = RequestMethod.GET, value="/{id}/poruke")
	public ResponseEntity<List<PorukaDTO>> getPoruke(
			@PathVariable Long id,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Poruka> poruke = porukaService.findByZgradaId(id, pageNum);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(poruke.getTotalPages()) );
		return  new ResponseEntity<>(
				toPorukaDTO.convert(poruke.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@ExceptionHandler(value= {DataIntegrityViolationException.class, IllegalArgumentException.class})
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
