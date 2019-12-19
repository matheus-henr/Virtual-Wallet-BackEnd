package br.com.virtual_wallet.model;

public class Categoria {

	private String nome;
	private Integer valor;
	
	public Categoria(String nome, Integer valor){
		this.nome = nome;
		this.valor = valor;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public Integer getValor() {
		return valor;
	}
}
