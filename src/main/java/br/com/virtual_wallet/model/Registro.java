package br.com.virtual_wallet.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Registro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Date data;
	private Double totReceita;
	private Double totDespesa;
	@OneToOne
	@JsonIgnore
	private Usuario usuario;
	
	public Registro() {}
	
	public Registro(Integer id, Date data, Double totReceita, Double totDespesa,Integer usuarioID) {
		super();
		this.usuario = new Usuario();
		this.id = id;
		this.data = data;
		this.totReceita = totReceita;
		this.totDespesa = totDespesa;
		this.usuario.setId(usuarioID);
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Double getTotReceita() {
		return totReceita == null ? 0.0 : totReceita;
	}



	public void setTotReceita(Double totReceita) {
		this.totReceita = totReceita;
	}



	public Double getTotDespesa() {
		return totDespesa == null ? 0.0 : totDespesa;
	}



	public void setTotDespesa(Double totDespesa) {
		this.totDespesa = totDespesa;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Double getSaldo(){
		return getTotReceita() - getTotDespesa();
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	
	
	
}
