package br.com.virtual_wallet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.virtual_wallet.model.Registro;
import br.com.virtual_wallet.model.Usuario;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {

	@Transactional(readOnly=true)
	Page<Registro> findByUsuario(Usuario usuarioLogado, Pageable pageable);

}
