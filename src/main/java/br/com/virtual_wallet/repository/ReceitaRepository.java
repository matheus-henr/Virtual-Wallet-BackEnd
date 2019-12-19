package br.com.virtual_wallet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.virtual_wallet.model.Receita;
import br.com.virtual_wallet.model.Usuario;


@Repository
public interface ReceitaRepository extends PagingAndSortingRepository<Receita, Integer> {

	
	@Transactional(readOnly=true)
	Page<Receita> findByUsuario(Usuario Usuario, Pageable pageable);
	
	
	@Query(value = "select * from receita r where r.usuario_id = (:id) ORDER BY data DESC"
			, nativeQuery=true)
	public List<Receita> buscarOrdenado(@Param("id") Integer id);
	
	@Query(value="select count(r.id) from receita r where r.usuario_id = (:id)", nativeQuery=true )
	public Integer qntReceitas(@Param("id") Integer id);
	
	@Query(value="select sum(r.valor) from receita r WHERE r.usuario_id = (:id) and  MONTH(data) =  MONTH(now()) and   "
			+ "   YEAR(data) =  YEAR(now())", nativeQuery=true)
	public Double totalReceitas(@Param("id") Integer id);
	
	@Query(value="select * from receita r WHERE r.usuario_id = (:id) and  MONTH(data) =  MONTH(now())" + 
			"and      YEAR(data) =  YEAR(now()) ORDER BY data DESC LIMIT 5"
			, nativeQuery=true)
	public List<Receita> ultimasReceita(@Param("id") Integer id);

	@Query(value="select * from receita r where r.usuario_id = (:id) and r.categoria = :categoria ", nativeQuery=true)
	public List<Receita> byCategoria(@Param("categoria") String categoria, @Param("id") Integer id);

	@Query(value="select * from receita r where r.usuario_id = (:id) and r.data BETWEEN (:data_inicio) and (:data_fim)", nativeQuery=true)
	public List<Receita> findByMes(@Param("data_inicio") String dataInicio, 
			@Param(value="data_fim") String dataFim, @Param("id") Integer id);
	
	@Query(value="select sum(r.valor) from receita r where r.usuario_id = (:id) and MONTH(data) =  (:mes) and  "
			+ "    YEAR(data) =  (:ano)", nativeQuery=true)
	public Double totalReceitas(@Param("mes") int mes, @Param("ano") int ano,  @Param("id") Integer id);
}
