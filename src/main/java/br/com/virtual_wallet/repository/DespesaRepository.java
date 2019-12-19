package br.com.virtual_wallet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.virtual_wallet.model.Despesa;
import br.com.virtual_wallet.model.Usuario;

@Repository
public interface DespesaRepository  extends PagingAndSortingRepository<Despesa, Integer>{
	
	
	@Transactional(readOnly=true)
	Page<Despesa> findByUsuario(Usuario Usuario, Pageable pageable);
	
	

	@Query(value="select * from despesa  d where d.usuario_id = (:id) ORDER BY data DESC;"
			, nativeQuery=true)
	public List<Despesa> buscarOrdenado(@Param("id") Integer id);
	
	@Query(value="select count(d.id) from despesa d where d.usuario_id = (:id)", nativeQuery=true )
	public Integer qntDespesa(@Param("id") Integer id);
	
	@Query(value="select sum(d.valor) from despesa d WHERE   MONTH(data) =  MONTH(now())" + 
			"and      YEAR(data) =  YEAR(now()) and d.usuario_id = (:id) ", nativeQuery=true)
	public Double totalDespesas(@Param("id") Integer id);

	@Query(value=" select * from despesa d WHERE  d.usuario_id = (:id) and   MONTH(data) =  MONTH(now()) "
			+ "and YEAR(data) =  YEAR(now()) ORDER BY data DESC LIMIT 5"
			, nativeQuery=true)
	public List<Despesa> ultimasDespesas(@Param("id") Integer id);

	@Query(value="select * from despesa d where d.usuario_id = (:id) and  d.categoria = :categoria ", nativeQuery=true)
	public List<Despesa> byCategoria(@Param("categoria") String categoria, @Param("id") Integer id);

	@Query(value="select * from despesa d where  d.usuario_id = (:id) and d.data BETWEEN (:data_inicio) and (:data_fim)", nativeQuery=true)
	public List<Despesa> findByMes(@Param("data_inicio") String dataInicio, 
			@Param(value="data_fim") String dataFim, @Param("id") Integer id);
	
	@Query(value="select sum(d.valor) from despesa d where  d.usuario_id = (:id) and MONTH(data) =  (:mes) " + 
			"and YEAR(data) = (:ano) ORDER BY", nativeQuery=true)
	public Double  totalDespesas(@Param("mes") int mes, @Param("ano") int ano,  @Param("id") Integer id);


}

