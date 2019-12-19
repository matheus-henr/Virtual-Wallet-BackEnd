package br.com.virtual_wallet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.virtual_wallet.dto.ReceitaDTO;
import br.com.virtual_wallet.model.Receita;
import br.com.virtual_wallet.service.ReceitaService;

@RestController
@RequestMapping("/receita")
@CrossOrigin("${origem-permitida}")
public class ReceitaController {

	@Autowired
	private ReceitaService service;
	
	
	@GetMapping
	public ResponseEntity<?> FindDynamics(Pageable pageable){
		 Page<Receita> receita = service.findAll(pageable);
		return new ResponseEntity<>(receita,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/receita-quantidade")
	public ResponseEntity<Integer> qntReceita(){
		Integer qntReceitas = service.qntReceita();
		if(qntReceitas == null) qntReceitas = 0;
		return new ResponseEntity<Integer>(qntReceitas, HttpStatus.OK);
	}
	
	@GetMapping("/receita-total")
	public ResponseEntity<Double> totalReceita(){
		Double  totalReceita = service.totalReceitas();
		if(totalReceita == null) totalReceita = 0.0;
		return new ResponseEntity<Double>(totalReceita, HttpStatus.OK);
	}
	
	@GetMapping("/receita-ultimas")
	public ResponseEntity<List<Receita>> ultimasReceitas(){
		List<Receita> ultimasReceita = service.ultimasReceitas();
		return new ResponseEntity<>(ultimasReceita, HttpStatus.OK);
	}
	@GetMapping("/filtro-categoria/{categoria}")
	public ResponseEntity<List<Receita>> findByCategoria(@PathVariable String categoria){
		  List<Receita> desepesasPorCategoria = service.byCategoria(categoria);
		return new ResponseEntity<>(desepesasPorCategoria, HttpStatus.OK);
		
	}
	
	@GetMapping("/filtro-data/")
	public ResponseEntity<List<Receita>> findByMes(
			@RequestParam("data-inicio") String dataInicio,
				@RequestParam("data-final") String dataFinal){
		  List<Receita> receitaPorMes = 
				  service.findByMes(dataInicio,dataFinal);
		return new ResponseEntity<>(receitaPorMes, HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Receita> salve(@Valid @RequestBody ReceitaDTO receita){
		if(receita == null)  throw new IllegalArgumentException("Paramentro null");
		Receita receitaCadastrada = service.save(receita);
		return new ResponseEntity<Receita>(receitaCadastrada,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Receita> edit(@Valid @RequestBody ReceitaDTO receita){
		if(receita == null)  throw new IllegalArgumentException("Paramentro null");
		Receita receitaEdit = service.save(receita);
		return new ResponseEntity<Receita>(receitaEdit,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Receita> delete(@PathVariable int id){
		
		if(id==0) return new ResponseEntity<Receita>(HttpStatus.NOT_FOUND);
		
		Receita receita = service.findOne(id);
		service.delete(receita);
		return new ResponseEntity<Receita>(HttpStatus.OK);
	}
	
}
