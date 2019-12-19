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

import br.com.virtual_wallet.dto.DespesaDTO;
import br.com.virtual_wallet.model.Despesa;
import br.com.virtual_wallet.service.DespesaService;

@RestController
@RequestMapping("/despesas")
@CrossOrigin("${origem-permitida}")
public class DespesaController {

	@Autowired
	private DespesaService service;
	
	
	@GetMapping()
	public ResponseEntity<?> FindDynamics(Pageable pageable){
		 Page<Despesa> despesas = service.findAll(pageable);
		 
		return new ResponseEntity<>(despesas,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/despesa-quantidade")
	public ResponseEntity<Integer> qntDespesas(){
		Integer qntDespesas = service.qntDespesa();
		if(qntDespesas == null) qntDespesas = 0;
		return new ResponseEntity<Integer>(qntDespesas, HttpStatus.OK);
	}
	
	@GetMapping("/despesa-total")
	public ResponseEntity<Double> totalDespesas(){
		Double  totalDespesas = service.totalDespesas();
		if(totalDespesas == null) totalDespesas = 0.0;
		return new ResponseEntity<Double>(totalDespesas, HttpStatus.OK);
	}
	
	@GetMapping("/despesa-ultimas")
	public ResponseEntity<List<Despesa>> ultimasDespesas(){
		  List<Despesa> ultimasDespesas = service.ultimasDespesas();
		return new ResponseEntity<>(ultimasDespesas, HttpStatus.OK);
	}
	
	@GetMapping("/filtro-categoria/{categoria}")
	public ResponseEntity<List<Despesa>> findByCategoria(@PathVariable String categoria){
		  List<Despesa> desepesasPorCategoria = service.byCategoria(categoria);
		return new ResponseEntity<>(desepesasPorCategoria, HttpStatus.OK);
		
	}
	
	@GetMapping("/filtro-data/")
	public ResponseEntity<List<Despesa>> findByMes(
			@RequestParam("data-inicio") String dataInicio,
				@RequestParam("data-final") String dataFinal){
		  List<Despesa> desepesasPorMes = 
				  service.findByMes(dataInicio,dataFinal);
		return new ResponseEntity<>(desepesasPorMes, HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Despesa> salve(@RequestBody DespesaDTO despesa){
		if(despesa == null) throw new IllegalArgumentException("Despesa null");
		Despesa despesaCadastrada = service.save(despesa);
		return new ResponseEntity<Despesa>(despesaCadastrada,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Despesa> edit(@Valid @RequestBody DespesaDTO despesa){
		if(despesa == null) throw new IllegalArgumentException("Despesa null");
		Despesa despesaEdit = service.save(despesa);
		return new ResponseEntity<Despesa>(despesaEdit,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Despesa> delete(@PathVariable int id){
		
		if(id==0) return new ResponseEntity<Despesa>(HttpStatus.NOT_FOUND);
		
		Despesa despesa = service.findOne(id);
		service.delete(despesa);
		return new ResponseEntity<Despesa>(HttpStatus.OK);
	}
	
	
	
}
