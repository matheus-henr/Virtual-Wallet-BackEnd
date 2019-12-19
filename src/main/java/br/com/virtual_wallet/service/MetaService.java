package br.com.virtual_wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.virtual_wallet.BO.MetaBO;
import br.com.virtual_wallet.dto.MetaDTO;
import br.com.virtual_wallet.model.Meta;
import br.com.virtual_wallet.model.Usuario;
import br.com.virtual_wallet.repository.MetaRepository;
import br.com.virtual_wallet.repository.UsuarioRepository;
import br.com.virtual_wallet.security.UserSS;
import br.com.virtual_wallet.service.exeception.AuthorizationException;
import br.com.virtual_wallet.service.exeception.CreationInvalidException;

@Service
public class MetaService {

	@Autowired
	private MetaRepository repository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private MetaBO bo;
	
	
	public Meta save(Meta meta) {
		if(meta == null) throw new IllegalArgumentException();
		
		Meta metaTest = repository.findByUsuario(getUsuarioLogado());
		if(metaTest != null && meta.getId() == null) throw new CreationInvalidException("Já existe uma Meta cadastrada "
				+ "para esse Usuario");
		meta.setUsuario(getUsuarioLogado());
		return repository.save(meta);
	}
	
	public MetaDTO status() {
		Meta meta = repository.findByUsuario(getUsuarioLogado());
		MetaDTO dto = bo.calculoMeta(meta);
		dto = bo.isUtrapassada(dto);
		return dto;
	}
	
	
	private Usuario getUsuarioLogado() {
		UserSS user = UserService.authenticated();
		
		if(user == null) throw new AuthorizationException("Acesso Negado");
		return  usuarioRepository.findOne(user.getId());
	}
	
}
