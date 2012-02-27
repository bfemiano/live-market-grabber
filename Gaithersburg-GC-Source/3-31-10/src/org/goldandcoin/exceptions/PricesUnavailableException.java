package org.goldandcoin.exceptions;

public class PricesUnavailableException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PricesUnavailableException() {
		super("Prices Unavailable from Market");
	}
}
