package br.com.virtual_wallet.model.enums;

public enum CategoriaDespesa {
	ALIMENTACAO(0,"Alimentação"), LAZER(1,"Lazer"), EDUCACAO(2,"Educação"),
	SAUDE(3,"Saúde"), PAGAMENTO(4,"Pagamento"), TRASPORTE(5,"Trasporte"), BELEZA(6,"Beleza"),
	INTERNET_TELEFONE(7,"Internet/Telefone"), MORADIA(8,"Moradia"),ROUPA(9,"Roupa"),
	VIAGEM(10,"Viagem"), BAR_SORVETERIA(11,"Bar/SOrveteria"), OUTROS(12,"Outros");
	
	
	private Integer cod;
	private String desc;

	CategoriaDespesa(Integer cod, String desc){
		this.cod = cod;
		this.desc = desc;
		
	}
	
	public String getDesc() {
		return desc;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	
	
	public static CategoriaDespesa getCategoria(Integer cod) {
		if(cod == null) {
			return  null;
		}
		
		for(CategoriaDespesa x : CategoriaDespesa.values()) {
			if(cod.equals(x.getCod())) return x;		
		}
		
		throw new IllegalArgumentException(cod + " Invalido" );
	}
}
