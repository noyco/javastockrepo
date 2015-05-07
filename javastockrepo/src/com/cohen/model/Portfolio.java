package com.cohen.model;

import com.cohen.Stock;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Portfolio {
	
	private final static int MAX_PROTFOLIO_SIZE = 5;
	
	private String title ;
	private Stock stocks [] = new Stock [MAX_PROTFOLIO_SIZE];
	private int protfolioSize;
	

	public Portfolio () {
		this.title = " ";
		this.stocks = new Stock [MAX_PROTFOLIO_SIZE];
		this.protfolioSize = 0;
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
	
	public void addStock (Stock stock){
		if(stock != null && protfolioSize < MAX_PROTFOLIO_SIZE) {
			this.stocks[protfolioSize] = stock;
			protfolioSize++;
		}
		else {
			System.out.println("Sorry - full or null!");
		}
	}
	
	public String getHtmlString (){
		String ret ="<br><h1>The title is: " + getTitle() + ".</h1></br>";
		
		for(int i=0 ; i < protfolioSize ; i++){
			ret = ret + this.stocks[i].getHtmlDescription();
		}
		return ret;
	}

}
