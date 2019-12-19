package br.com.virtual_wallet.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.virtual_wallet.model.enums.CategoriaDespesa;

@Entity
public class Despesa {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull
	private Double valor;
	@NotBlank
	private String descricao;
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date data;
	@NotNull
	private Integer categoria;
	private boolean pago;
	@OneToOne
	private Carteira carteira;
	
	@OneToOne
	@JsonIgnore
	private Usuario usuario;
	
	
	
	
	public Despesa() {
	}
	
	
	
	
	public Despesa(Integer id, Double valor, String descricao, Date data, Integer categoria, boolean pago,
			Usuario usuario) {
		super();
		this.id = id;
		this.valor = valor;
		this.descricao = descricao;
		this.data = data;
		this.categoria = categoria;
		this.pago = pago;
		this.usuario = usuario;
	}
	
	public Despesa(Integer id, Double valor, String descricao, Date data, Integer categoria, boolean pago,
			Integer usuarioId) {
		this.usuario = new Usuario();
		this.id = id;
		this.valor = valor;
		this.descricao = descricao;
		this.data = data;
		this.categoria = categoria;
		this.pago = pago;
		this.usuario.setId(usuarioId);
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
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	public Integer getId() {
		return id;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getCategoriaNome() {
		return CategoriaDespesa.getCategoria(getCategoria()).getDesc();
	}
	
	
	
	public Carteira getCarteira() {
		return carteira;
	}




	public void setCarteira(Carteira carteira) {
		this.carteira = carteira;
	}




	public Usuario getUsuario() {
		return usuario;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Despesa other = (Despesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
