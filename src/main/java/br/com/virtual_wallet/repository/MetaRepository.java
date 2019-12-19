package br.com.virtual_wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.virtual_wallet.model.Meta;
import br.com.virtual_wallet.model.Usuario;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Integer>{
	
	@Transactional(readOnly=true)
	public Meta findByUsuario(Usuario usuario);
	
}
