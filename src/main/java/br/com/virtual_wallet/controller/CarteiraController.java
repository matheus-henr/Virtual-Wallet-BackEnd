package br.com.virtual_wallet.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.virtual_wallet.dto.CarteiraDTO;
import br.com.virtual_wallet.model.Carteira;
import br.com.virtual_wallet.service.CarteiraService;

@RestController
@RequestMapping("/carteira")
@CrossOrigin("${origem-permitida}")
public class CarteiraController {

	@Autowired
	private CarteiraService service;
	
	@PostMapping
	public ResponseEntity<Carteira> save(@Valid @RequestBody Carteira carteira){
		
		 carteira = service.create(carteira);
		
		return new ResponseEntity<Carteira>(carteira,HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<CarteiraDTO> find(){
		
		 CarteiraDTO carteira= service.findAll();
		
		return new ResponseEntity<CarteiraDTO>(carteira,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Carteira> update(@Valid @RequestBody Carteira carteira){
		
		 carteira = service.create(carteira);
		
		return new ResponseEntity<Carteira>(carteira,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Carteira> delete(@PathVariable int id){
		
		 service.deletar(id);
		
		return new ResponseEntity<Carteira>(HttpStatus.CREATED);
	}
	
	
}
