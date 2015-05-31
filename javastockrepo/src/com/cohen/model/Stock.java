package com.cohen.model;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.algo.model.StockInterface;

import com.cohen.model.Portfolio.ALGO_RECOMMENDATION;
/**
 * stock class - saves information about a stock, symbol, ask, bid, date, recommendation and stockQuantity.
 * @author noyco
 *
 */

public class Stock implements StockInterface {

	
	private String symbol ; 
	private float ask;
	private float bid;
	private java.util.Date date;
	private Portfolio.ALGO_RECOMMENDATION recommendation;
	private int quantity;
	
	public Stock (){
		this.symbol = new String();
		this.bid = 0;
		this.ask = 0;
		this.date = new Date();
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.quantity = 0;				
	}
	
	/**
	 * c'tor method
     * Gets from the user  information about a stock.
	 * @param symbol 
	 * @param ask
	 * @param bid
	 * @param date
	 * @param recommendation
	 * @param stockQuantity
	 */

	public Stock (String symbol, float ask, float bid, Date date){
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.quantity = 0;
	}
	
	/**
	 * copy c'tor method
	 * @param stock - Gets from the user the stock he wants to copy.
	 */
	public Stock (Stock stock){
		this.symbol = new String (stock.getSymbol());
		this.ask = stock.getAsk();
		this.bid = stock.getBid();
		this.date = new Date( stock.getDate().getTime());
		this.recommendation = stock.getRecommendation();
		this.quantity = stock.getQuantity();
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
	public Portfolio.ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(Portfolio.ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int stockQuantity) {
		this.quantity = stockQuantity;
	}
	
	/**
	 * string method that print to screen
	 * @return string value
	 */
	public String getHtmlDescription(){
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dataStr = df.format(getDate());
		
		String ret = "<br>"+" <b> Symbol:  </b>" +getSymbol()+" <b> Ask:  </b>"+getAsk()+" <b> Bid:  </b>"+getBid()+" <b> Date:  </b>"+getDate()+" <b> recommendation:  </b>"+getRecommendation()+" <b> Quantity:  </b>"+getQuantity()+"</br>";
		
		return ret;
	}
	
	
	
}

