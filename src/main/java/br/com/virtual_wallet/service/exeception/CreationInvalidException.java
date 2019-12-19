package br.com.virtual_wallet.service.exeception;

public class CreationInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CreationInvalidException(String msg) {
		super(msg);
	}
	
	public CreationInvalidException(String msg, Throwable causa) {
		super(msg,causa);
	}
	

}
