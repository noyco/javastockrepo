package com.cohen.model;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Stock  {

	
	private String symbol ; 
	private float ask;
	private float bid;
	private java.util.Date date;
	private int recommendation;
	private int stockQuantity;
	
	public enum Status {
		BUY,  SELL, REMOVE , HOLD
	}
	

	public Stock (String symbol, float ask, float bid, Date date, int recommendation, int stockQuantity){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.recommendation = recommendation;
		this.stockQuantity = stockQuantity;
	}
	
	public Stock (Stock stock){
		this.symbol = new String (stock.getSymbol());
		this.ask = stock.getAsk();
		this.bid = stock.getBid();
		this.date = new Date( stock.getDate().getTime());
		this.recommendation = stock.getRecommendation();
		this.stockQuantity = stock.getStockQuantity();
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public int getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getHtmlDescription(){
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dataStr = df.format(getDate());
		
		String ret = "<br>"+" <b> Symbol:  </b>" +getSymbol()+" <b> Ask:  </b>"+getAsk()+" <b> Bid:  </b>"+getBid()+" <b> Date:  </b>"+getDate()+" <b> recommendation:  </b>"+getRecommendation()+" <b> Quantity:  </b>"+getStockQuantity()+"</br>";
		
		return ret;
	}
	
	
	
}
