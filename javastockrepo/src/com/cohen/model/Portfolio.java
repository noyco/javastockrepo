package com.cohen.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
/** 
 * portfolio class - a array with value and details of stocks, a title and physical size of the stocks array
 * @author noyco
 */

public class Portfolio {
	
	private final static int MAX_PROTFOLIO_SIZE = 5;
	
	private String title ;
	private Stock stocks [] = new Stock [MAX_PROTFOLIO_SIZE];
	private int protfolioSize;
	
/** 
 * c'tor method
 * @param title - Gets from the user the name of the portfolio.
 */
	public Portfolio (String title) {
		this.title = title;
		this.stocks = new Stock [MAX_PROTFOLIO_SIZE];
		this.protfolioSize = 0;
	}
	
	/** 
	 * copy c'tor method
	 * @param portfolio - Gets from the user the portfolio he wants to copy.
	 */
	
	public Portfolio (Portfolio portfolio){
		this.title = new String (portfolio.getTitle());
		this.protfolioSize = portfolio.getProtfolioSize();
		
		Stock [] coppied = portfolio.getStocks();
		for (int i = 0; i < this.protfolioSize; i++){
			this.stocks [i] = new Stock (coppied [i]);
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Stock[] getStocks() {
		return stocks;
	}
	
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	
	public static int getMaxProtfolioSize() {
		return MAX_PROTFOLIO_SIZE;
	}
	
	public int getProtfolioSize() {
		return protfolioSize;
	}
	
	public void setProtfolioSize(int protfolioSize) {
		this.protfolioSize = protfolioSize;
	}
	/**
	 * add stock method
	 * @param stockAd - Gets from the user the stock he wants to add to the array.
	 */
	public void addStock (Stock stockAd){
		if(stockAd != null && protfolioSize < MAX_PROTFOLIO_SIZE) {
			this.stocks[protfolioSize] = stockAd;
			protfolioSize++;
		}
		else {
			System.out.println("Sorry - full or null!");
		}
	}
	
	/**
	 * remove stock method
	 * @param symbol - Gets from the user the symbol to the stock he wants to remove from the array.
	 */
	public void removeStock (String symbol){
		for (int i=0; i < protfolioSize; i++){
			
			if (this.stocks[i].getSymbol().equals(symbol)){
				
				this.stocks[i] = this.stocks[protfolioSize-1];
				this.stocks[protfolioSize-1] = null;
				protfolioSize--;
			}
		}
	}
	/**
	 * string method that print to screen
	 * @return string value
	 */
	public String getHtmlString (){
		String ret ="<br><h1>The title is: " + getTitle() + ".</h1></br>";
		
		for(int i=0 ; i < protfolioSize ; i++){
			ret = ret + this.stocks[i].getHtmlDescription();
		}
		return ret;
	}
}
