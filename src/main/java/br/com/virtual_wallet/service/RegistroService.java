package br.com.virtual_wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.virtual_wallet.model.Registro;
import br.com.virtual_wallet.model.Usuario;
import br.com.virtual_wallet.repository.RegistroRepository;
import br.com.virtual_wallet.security.UserSS;
import br.com.virtual_wallet.service.exeception.AuthorizationException;

@Service
public class RegistroService {
	
	@Autowired
	private RegistroRepository repository;
	@Autowired
	private UsuarioService usuarioService;
	
	public Registro save(Registro registro) {
		return  repository.save(registro);
	}
	
	public Page<Registro> find(Pageable pageable){
		return repository.findByUsuario(getUsuarioLogado(), pageable);
	}
	
	

	private Usuario getUsuarioLogado() {
		UserSS user = UserService.authenticated();
		
		if(user == null) throw new AuthorizationException("Acesso Negado");
		return  usuarioService.findOne(user.getId());
	}
}
