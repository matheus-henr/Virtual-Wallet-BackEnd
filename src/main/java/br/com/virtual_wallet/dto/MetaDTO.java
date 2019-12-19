package br.com.virtual_wallet.dto;

import java.io.Serializable;

public class MetaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double meta;
	private Double valorTotal;
	private Double diferenca;
	private boolean utrapassada;
	
	
	public Double getMeta() {
		return meta;
	}
	public void setMeta(Double meta) {
		this.meta = meta;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Double getDiferenca() {
		return diferenca;
	}
	public void setDiferenca(Double diferenca) {
		this.diferenca = diferenca;
	}
	
	public void setUtrapassada(boolean utrapassada) {
		this.utrapassada = utrapassada;
	}
	public boolean isUtrapassada() {
		return utrapassada;
	}
	
	
	
	
	
	
	
}
