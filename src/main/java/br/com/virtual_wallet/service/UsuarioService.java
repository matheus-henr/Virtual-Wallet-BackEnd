package br.com.virtual_wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.virtual_wallet.dto.UsuarioDTO;
import br.com.virtual_wallet.model.Usuario;
import br.com.virtual_wallet.repository.UsuarioRepository;
import br.com.virtual_wallet.security.UserSS;
import br.com.virtual_wallet.service.exeception.AuthorizationException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private BCryptPasswordEncoder pe;
	private UserSS user;
	
	
	public Usuario save(UsuarioDTO usuario) {
		return repository.save(fromDTO(usuario));
	}
	
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	
	public Usuario findOne(Integer id) {
		user = null;
		 user = UserService.authenticated();
		
		if(user == null && !id.equals(user.getId())) 
			throw new AuthorizationException("Acesso Negado");
		
		return repository.findOne(id);
	}
	
	public Usuario update(String senha){
		Usuario usu = getUsuarioLogado();
		if(usu == null) {
			throw new IllegalAccessError("Usuario indisponivel");
		}
		
		usu.setSenha(pe.encode(senha));
		return repository.save(usu);
	}
	
	public Usuario fromDTO(UsuarioDTO dto) {
		return new Usuario(dto.getId(), dto.getNome(), dto.getSobrenome(), dto.getEmail(), pe.encode(dto.getSenha()));
	}
	
	


	public Usuario getUsuarioLogado() {
		UserSS user = UserService.authenticated();
		
		if(user == null) throw new AuthorizationException("Acesso Negado");
		return  findOne(user.getId());
	}
}
