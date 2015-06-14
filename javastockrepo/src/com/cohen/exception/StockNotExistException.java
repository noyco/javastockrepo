package com.cohen.exception;

import org.algo.exception.PortfolioException;

public class StockNotExistException extends PortfolioException {
	public StockNotExistException(){
		super("The stock doesn't exist");
	}
	
	public StockNotExistException (String symbol){
		super ("The stock " +symbol+ " does not exist");
				
	}

}
