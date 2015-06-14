package com.cohen.exception;

import org.algo.exception.PortfolioException;

public class BalanceException extends PortfolioException {
	
	public BalanceException (){
		super("There is not enough BALANCE");
	}
	
	public BalanceException (String error){
		super(error);
	}
}






