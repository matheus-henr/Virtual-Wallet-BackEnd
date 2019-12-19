package br.com.virtual_wallet.controller;


/**
 * @author Matheus Henrique
 * 
 **/


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.virtual_wallet.model.Categoria;
import br.com.virtual_wallet.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("${origem-permitida}")
public class CategoriaController {
	
	@Autowired
	 private CategoriaService categoriaBO;

	/**
	 * Pega as categorias possiveis para Receitas ou Despesas
	 *@param categoria String -  Recebe o tipo de categoria
	 *@return List - Lista de catogorias.
	 *@see O paramentro categoria recebe dois valores possiveis, despesa & receita 
	 *
	 **/
	
	@GetMapping("/{categoria}")
	public List<Categoria> findCategoria(@PathVariable String categoria) {	
		return categoriaBO.getCategoria(categoria);
	}	
}