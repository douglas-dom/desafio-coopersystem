package org.coop.cotacao.exceptions;

public class ParametroInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3356253696307058458L;
	
	public ParametroInvalidoException(String message) {
		super(message);
	}
	
	@Override
	  public String getMessage(){
	    return "Não existe letra B em sua frase";
	  }

}
