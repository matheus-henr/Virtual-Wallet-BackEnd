package br.com.virtual_wallet.service.exeception;

public class LimitExceededException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LimitExceededException(String msg) {
		super(msg);
	}
	
	public LimitExceededException(String msg, Throwable causa) {
		super(msg,causa);
	}
	

}
