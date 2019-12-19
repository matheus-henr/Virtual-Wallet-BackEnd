package br.com.virtual_wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.virtual_wallet.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	
	@Transactional(readOnly=true)
	Usuario findByEmail(String email);

	@Query(value="select u.id from usuario u", nativeQuery=true)
	List<Integer> findAllID();
	
}
