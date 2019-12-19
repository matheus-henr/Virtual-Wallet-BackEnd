package br.com.virtual_wallet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotEmpty(message="Nome não pode ficar vazio")
	private String firstNome;
	
	@NotEmpty(message="Nome não pode ficar vazio")
	private String lastNome;
	
	@NotEmpty(message="Email não pode ficar vazio")
	@Email
	private String email;
	
	private String senha;
	
	
	public Usuario() {}
	
	
	public Usuario(Integer id, String firstNome, String lastNome, String email, String senha) {
		super();
		this.id = id;
		this.firstNome = firstNome;
		this.lastNome = lastNome;
		this.email = email;
		this.senha = senha;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirstNome() {
		return firstNome;
	}


	public void setFirstNome(String firstNome) {
		this.firstNome = firstNome;
	}


	public String getLastNome() {
		return lastNome;
	}


	public void setLastNome(String lastNome) {
		this.lastNome = lastNome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}
