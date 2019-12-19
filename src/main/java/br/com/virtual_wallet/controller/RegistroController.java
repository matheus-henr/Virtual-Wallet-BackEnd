package br.com.virtual_wallet.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.virtual_wallet.model.Registro;
import br.com.virtual_wallet.service.RegistroService;

@RestController
@RequestMapping("/registro")
@CrossOrigin("${origem-permitida}")
public class RegistroController {

	@Autowired
	private RegistroService service;
	
	
	@GetMapping
	public ResponseEntity<Page<Registro>> find(Pageable pageable){
		Page<Registro> registros = service.find(pageable); 
		return new ResponseEntity<Page<Registro>>(registros,HttpStatus.OK);
	}
	
	
	
}
