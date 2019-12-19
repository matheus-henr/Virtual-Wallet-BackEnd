package br.com.virtual_wallet.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.virtual_wallet.dto.ReceitaDTO;
import br.com.virtual_wallet.model.Receita;
import br.com.virtual_wallet.model.Usuario;
import br.com.virtual_wallet.repository.ReceitaRepository;
import br.com.virtual_wallet.repository.UsuarioRepository;
import br.com.virtual_wallet.security.UserSS;
import br.com.virtual_wallet.service.exeception.AuthorizationException;

@Service
public class ReceitaService {
	
	@Autowired
	private ReceitaRepository repository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	private CarteiraService carteiraService;
	
	public Page<Receita> findAll(Pageable pageable) {
		return  repository.findByUsuario(getUsuarioLogado(), pageable);
	}

	public List<Receita> buscarOrdenado() {
		return repository.buscarOrdenado(getUsuarioLogado().getId());
	}

	public Integer qntReceita() {
		return repository.qntReceitas(getUsuarioLogado().getId());
	}

	public Double totalReceitas() {
		return repository.totalReceitas(getUsuarioLogado().getId());
	}
	
	public Double totalReceitas(Integer id, LocalDate data) {
		return repository.totalReceitas(data.getMonthValue(),data.getYear(), id);
	}

	public List<Receita> ultimasReceitas() {
		return repository.ultimasReceita(getUsuarioLogado().getId());
	}

	public List<Receita> byCategoria(String categoria) {
		return repository.byCategoria(categoria,getUsuarioLogado().getId());
	}

	public List<Receita> findByMes(String dataInicio, String dataFinal) {
		return repository.findByMes(dataInicio, dataFinal,getUsuarioLogado().getId());
	}

	public Receita save(ReceitaDTO receitaDTO) {
		Receita rec = fromDTO(receitaDTO);
		
		if(rec.isRecebido() == true) carteiraService.depositar(rec.getCarteira(),rec.getValor());
		
		return repository.save(rec);
	}

	public Receita findOne(int id) {
		return repository.findOne(id);
	}

	public void delete(Receita Receita) {
		repository.delete(Receita);
	}
	
	private Usuario getUsuarioLogado() {
		UserSS user = UserService.authenticated();
		
		if(user == null) throw new AuthorizationException("Acesso Negado");
		return  usuarioRepository.findOne(user.getId());
	}
	
	
	public Receita fromDTO(ReceitaDTO dto) {
		return new Receita(null, dto.getValor(), dto.getDescricao(), dto.getData(), dto.getCategoria(), dto.getRecebido(), getUsuarioLogado().getId());
	}
	
}
