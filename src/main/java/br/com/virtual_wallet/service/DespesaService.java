package br.com.virtual_wallet.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.virtual_wallet.dto.DespesaDTO;
import br.com.virtual_wallet.model.Despesa;
import br.com.virtual_wallet.model.Usuario;
import br.com.virtual_wallet.repository.DespesaRepository;
import br.com.virtual_wallet.repository.UsuarioRepository;
import br.com.virtual_wallet.security.UserSS;
import br.com.virtual_wallet.service.exeception.AuthorizationException;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository repository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CarteiraService carteiraService;
	
	public Page<Despesa> findAll(Pageable pageable) {
		Usuario usuario = getUsuarioLogado();
		return  repository.findByUsuario(usuario, pageable);
	}


	public List<Despesa> buscarOrdenado() {
		return repository.buscarOrdenado(getUsuarioLogado().getId());
	}

	public Integer qntDespesa() {
		return repository.qntDespesa(getUsuarioLogado().getId());
	}

	public Double totalDespesas() {
		return repository.totalDespesas(getUsuarioLogado().getId());
	}
	public Double totalDespesas(LocalDate date, int id) {
		return repository.totalDespesas(date.getMonthValue(),date.getYear(), id);
	}

	public List<Despesa> ultimasDespesas() {
		return repository.ultimasDespesas(getUsuarioLogado().getId());
	}

	public List<Despesa> byCategoria(String categoria) {
		return repository.byCategoria(categoria,getUsuarioLogado().getId());
	}

	public List<Despesa> findByMes(String dataInicio, String dataFinal) {
		return repository.findByMes(dataInicio, dataFinal,getUsuarioLogado().getId());
	}

	public Despesa save(DespesaDTO despesaDTO) {
		Despesa des = fromDTO(despesaDTO);
		
		if(des.isPago() == true) carteiraService.retirar(des.getCarteira(), des.getValor());
		
		
		des = repository.save(des);
		return des;
	}

	public Despesa findOne(int id) {
		return repository.findOne(id);
	}

	public void delete(Despesa despesa) {
		repository.delete(despesa);
	}
	
	private Usuario getUsuarioLogado() {
		UserSS user = UserService.authenticated();
		
		if(user == null) throw new AuthorizationException("Acesso Negado");
		return  usuarioRepository.findOne(user.getId());
	}
	
	
	public Despesa fromDTO(DespesaDTO dto) {
		Despesa  des = new  Despesa(null, dto.getValor(), dto.getDescricao(), dto.getData(), dto.getCategoria(), dto.getPago(), getUsuarioLogado().getId());
		return des;
	}
	
}
