package br.com.virtual_wallet.dto;

import java.io.Serializable;
import java.util.List;

import br.com.virtual_wallet.model.Carteira;

public class CarteiraDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Carteira> carteiras;
	
	private Double saldoTotal;
	
	public List<Carteira> getCarteiras() {
		return carteiras;
	}
	public void setCarteiras(List<Carteira> carteiras) {
		this.carteiras = carteiras;
	}
	public Double getSaldoTotal() {
		return saldoTotal == null ? 0.0: saldoTotal;
	}
	public void setSaldoTotal(Double saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	
	
	
}
