package com.cohen.exception;

import org.algo.exception.PortfolioException;

public class StockAlreadyExistsException extends PortfolioException {
	
	public StockAlreadyExistsException (){
		super("The stock already exists on file shares");
	}
	
	public StockAlreadyExistsException (String symbol){
		super("the stock " + symbol + " already exists in portfolio");
	}

}
