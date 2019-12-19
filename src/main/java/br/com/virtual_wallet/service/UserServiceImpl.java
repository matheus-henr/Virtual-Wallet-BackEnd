package br.com.virtual_wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.virtual_wallet.model.Usuario;
import br.com.virtual_wallet.repository.UsuarioRepository;
import br.com.virtual_wallet.security.UserSS;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario cli = repository.findByEmail(email);
		
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		
		
		
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha()); 
	}


}
