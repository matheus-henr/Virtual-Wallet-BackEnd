package br.com.virtual_wallet.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.virtual_wallet.dto.CarteiraDTO;
import br.com.virtual_wallet.model.Carteira;
import br.com.virtual_wallet.repository.CarteiraRepository;
import br.com.virtual_wallet.service.exeception.LimitExceededException;

@Service
public class CarteiraService {

	@Autowired 
	private CarteiraRepository repository;
	@Autowired
	UsuarioService usuarioService;
	
	
	public Carteira create(Carteira carteira) {
		if(repository.countByUsuario(usuarioService.getUsuarioLogado()) >= 3) {
			throw new LimitExceededException("Limite de carteiras execido");
		}
		carteira.setUsuario(usuarioService.getUsuarioLogado());
		return repository.save(carteira);
		
	}
	
	public CarteiraDTO findAll() {
		CarteiraDTO dto = new CarteiraDTO();
		dto.setCarteiras(repository.findByUsuario(usuarioService.getUsuarioLogado()));
		dto.getCarteiras().stream().forEach((carteira)-> dto.setSaldoTotal(dto.getSaldoTotal() + carteira.getSaldo()));
		return dto;
	}
	
	public void depositar(Carteira carteira, Double saldo) {
		carteira.setSaldo(carteira.getSaldo() + saldo);
		repository.save(carteira);
	}
	
	public void retirar(Carteira carteira, Double saldo) {
		carteira.setSaldo(carteira.getSaldo() - saldo);
		repository.save(carteira);
	}
	
	public void deletar(int id) {
		Carteira carteira = repository.findOne(id);
		repository.delete(carteira);
	}
	
	public void atualizar(Carteira carteira) {
		repository.save(carteira);
	}
}
