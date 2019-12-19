package br.com.virtual_wallet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Carteira {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	private String nome;
	private Double saldo;
	
	@OneToOne
	@JsonIgnore
	private Usuario usuario;

	
	public Carteira(Integer id, String nome, Integer usuarioID) {
		this.usuario = new Usuario();
		this.id = id;
		this.nome = nome;
		this.usuario.setId(usuarioID);
	}
	
	
	public Carteira(Integer id, String nome, Integer categoria, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
	}
	
	public Carteira() {}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public Double getSaldo() {
		return saldo == null ? 0.0 : saldo ;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
