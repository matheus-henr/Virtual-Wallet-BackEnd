package br.com.virtual_wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.virtual_wallet.model.Carteira;
import br.com.virtual_wallet.model.Usuario;
@Repository
public interface CarteiraRepository extends JpaRepository<Carteira,Integer> {

	@Transactional(readOnly=true)
	List<Carteira> findByUsuario(Usuario usuario);
	@Transactional(readOnly=true)
	Integer countByUsuario(Usuario usuario);
	
	
}
