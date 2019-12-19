package br.com.virtual_wallet.model.enums;

public enum CategoriaReceita {
	
	SALARIO(0,"Salário"), VENDAS(1,"Vendas"), INVESTIMENTO(2,"Investimento"),
	OUTROS(3,"Outros");
	
	
	private String desc;
	private Integer cod;
	
	private CategoriaReceita(Integer cod, String desc) {
		this.desc = desc;
		this.cod = cod;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	
	public static CategoriaReceita getCategoria(Integer cod) {
		if(cod == null) return null;
		
		for(CategoriaReceita x : CategoriaReceita.values()) {
			if(cod.equals(x.getCod())) return x;
		}
		
		throw new IllegalArgumentException(cod + "  invalido");
	}
}
