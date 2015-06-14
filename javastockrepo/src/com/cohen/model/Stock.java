package com.cohen.model;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.algo.model.StockInterface;
/**
 * this class represents a Stock of Stocks
 * @author noyco
 *
 */
public class Stock implements StockInterface
{	
	
	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD
	}
	

    private ALGO_RECOMMENDATION recommendation;
    private String symbol;
    private float ask, bid;
    private Date date;
    private int stockQuantity;
    transient private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  
    /**
	 * C'tor of stock
	 * 
	 * @param symbol - name of stock
	 * @param ask - ask value
	 * @param bid - bid value
	 * @param date - create date of the stock
	 * @author noyco
	 */  
	public Stock(String symbol, float ask, float bid, Date date, int quantity)
	{
	    this.symbol = symbol;
	    this.ask = ask;
	    this.bid = bid;
	    this.date = date;
	    this.stockQuantity = quantity;
	}
	
	/**
	 * copy c'tor of stock
	 * @param copyStock
	 */
	public Stock(Stock copyStock) 
	{
	    this.symbol = copyStock.getSymbol();
	    this.ask = copyStock.getAsk();
	    this.bid = copyStock.getBid();
	    this.date = copyStock.getDate();
	    this.recommendation = copyStock.getRecommendation();
	    this.stockQuantity = copyStock.getQuantity();
	}
	/** 
	 * c'tor method
	 * Creates an instance of Stock
	 * Set the ask, bid and stockQuantity start as 0.
	 * set recommendation as hold
	 */
	public Stock() 
	{
		this.symbol = new String();
		this.ask = 0;
		this.bid = 0;
		this.date = new Date();
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.stockQuantity = 0;
	}
	
	// methods
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return this.ask;
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
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getQuantity()
	{
		return this.stockQuantity;
	}
	
	public void setQuantity(int quantityToSet) 
	{ 
		this.stockQuantity = quantityToSet;
	}
	

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}
	
	/**
	 * string method that print to screen
	 * @return string value
	 */
	public String getHtmlDescription(){  
		String resultStr = "<b>Stocksymbol</b>: "
		+this.getSymbol()+  "<b> Bid</b>: "
		+this.getBid()+ "<b> Ask</b>: "
		+this.getAsk()+ "<b> Quantity</b>: "
		+ this.getQuantity() + "<b> Date:</b> "
		+ this.dateFormat.format(this.getDate());
		return resultStr;
	}
	
}


