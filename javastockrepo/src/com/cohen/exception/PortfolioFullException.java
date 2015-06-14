package com.cohen.exception;

import org.algo.exception.PortfolioException;

public class PortfolioFullException extends PortfolioException {
	
	public PortfolioFullException (){
		super("Portfolio is full - Added too much stocks than max stocks limitation");
	}

}
