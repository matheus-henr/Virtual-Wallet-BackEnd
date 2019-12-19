package br.com.virtual_wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.virtual_wallet.dto.MetaDTO;
import br.com.virtual_wallet.model.Meta;
import br.com.virtual_wallet.service.MetaService;

@RestController
@RequestMapping("/meta")
@CrossOrigin("${origem-permitida}")
public class MetaController {

	@Autowired
	private MetaService service;
	
	@PostMapping
	public ResponseEntity<Meta> save(@RequestBody @Valid Meta meta){
		meta = service.save(meta);
		return new ResponseEntity<Meta>(HttpStatus.CREATED);
	} 
	
	@GetMapping
	public ResponseEntity<MetaDTO> status(){
		MetaDTO dto = service.status();
		return new ResponseEntity<MetaDTO>(dto,HttpStatus.ACCEPTED);
	}
	
	@PutMapping
	public ResponseEntity<Meta> update(@RequestBody @Valid Meta meta){
		meta = service.save(meta);
		return new ResponseEntity<Meta>(HttpStatus.OK);
	} 
	
}
