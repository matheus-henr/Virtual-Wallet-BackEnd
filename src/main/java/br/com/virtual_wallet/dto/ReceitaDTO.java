package br.com.virtual_wallet.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReceitaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private  Integer id;
	@NotNull(message="Valor não pode ser NULO")
	private  Double valor;
	@NotBlank(message="Descrição invalida")
	private  String descricao;
	@NotNull(message="Data invalida")
	@Temporal(TemporalType.DATE)
	private  Date data;
	@NotNull(message="Categoria invalida")
	private  Integer categoria;
	private  boolean recebido;
	@NotNull
	private  Integer usuario;
	@NotNull
	private Integer carteira;
	
	
	
	
	public ReceitaDTO() {
	}
	
	
	
	
	
	public ReceitaDTO(Integer id, Double valor, String descricao, Date data, Integer categoria, Integer usuario) {
		super();
		this.id = id;
		this.valor = valor;
		this.descricao = descricao;
		this.data = data;
		this.categoria = categoria;
		this.usuario = usuario;
	}





	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	
	public void setRecebido(boolean Recebido) {
		this.recebido = Recebido;
	}
	
	public boolean getRecebido() {
		return recebido;
	}
	
	public Integer getUsuario() {
		return usuario;
	}
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}


	public Integer getCarteira() {
		return carteira;
	}

	public void setCarteira(Integer carteira) {
		this.carteira = carteira;
	}
	
	
	
	
	
}
