package br.com.virtual_wallet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.virtual_wallet.model.Categoria;
import br.com.virtual_wallet.model.enums.CategoriaDespesa;
import br.com.virtual_wallet.model.enums.CategoriaReceita;

@Service
public class CategoriaService {
	
	List<Categoria> categoriaList;
	
	public List<Categoria> getCategoria(String categoria) {
		
		
		if (categoria.equals("despesa")) {
			return getCategoriaDespesas();
			
		} else if (categoria.equals("receita"))
			return getCategoriaReceitas();
		
		throw new IllegalArgumentException("Paramentro não conhecido!");
	}
	
	private List<Categoria> getCategoriaDespesas(){
		categoriaList = new ArrayList<>();
		for (CategoriaDespesa categ : CategoriaDespesa.values())
			categoriaList.add(new Categoria(categ.getDesc(), categ.getCod()));
		return returnListOrdered(categoriaList);
	}
	
	private List<Categoria> getCategoriaReceitas(){
		categoriaList = new ArrayList<>();
		for (CategoriaReceita categ : CategoriaReceita.values())
			categoriaList.add(new Categoria(categ.getDesc(), categ.getCod()));
	return returnListOrdered(categoriaList);
	}
	
	private List<Categoria> returnListOrdered(List<Categoria> lista){
		return lista.stream().
				sorted((c1, c2) -> c1.getNome().compareTo(c2.getNome()))
				.collect(Collectors.toList());
	}

	
}
