package com.cohen.model;

import java.util.Date;
import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

@SuppressWarnings("serial")
/** 
 * portfolio class - a array with value and details of stocks, a title and physical size of the stocks array
 * @author noyco
 */

public class Portfolio implements PortfolioInterface {
	
	
	final static int MAX_PROTFOLIO_SIZE = 5;
	
	public enum ALGO_RECOMMENDATION{
		
		BUY, SELL, REMOVE, HOLD;
	}
	
	private String title ;
	private StockInterface [] stocks ;
	private int protfolioSize;
	private float balance ;
	
/** 
 * c'tor method
 * @param title - Gets from the user the name of the portfolio.
*/
	public Portfolio () {
		this.title =new String ("temp title");
		this.stocks = new Stock [MAX_PROTFOLIO_SIZE];
		this.protfolioSize = 0;
		this.balance = 0;
	}
	
	/** 
	 * copy c'tor method
	 * @param portfolio - Gets from the user the portfolio he wants to copy.
	 */
	public Portfolio(Portfolio portfolioToCopy) 
	{
		this.title = portfolioToCopy.getTitle();
		for (int i=0; i < portfolioToCopy.getProtfolioSize(); i++)
		{
			String symbol = portfolioToCopy.stocks[i].getSymbol();
			float ask = portfolioToCopy.stocks[i].getAsk();
			float bid = portfolioToCopy.stocks[i].getBid();
			Date date = portfolioToCopy.stocks[i].getDate();
			int quantity = ((Stock) portfolioToCopy.stocks[i]).getQuantity(); // CASTED
			Stock stock = new Stock(symbol, ask, bid, date, quantity);
			this.stocks[i] = stock;
		}
		this.protfolioSize = portfolioToCopy.getProtfolioSize();
	}

	
	public Portfolio(StockInterface[] stocksArray) {
		this.protfolioSize = stocksArray.length;
		 this.title = new String("Temp Title");
		this.stocks = new StockInterface[MAX_PROTFOLIO_SIZE];
		this.balance = 0;
		for(int i = 0; i<this.protfolioSize; i++){
			this.stocks[i]= new Stock ((Stock)stocksArray[i]);
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public StockInterface[] getStocks() {
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
	
	public float getBalance() {
		return balance;
	}
	
	public void updateBalance (float amount){
		if(this.balance + amount < 0){
			System.out.println("You can't go into overdraft");	
		}
		else {
			this.balance += amount;	
		}
	}
	
	/**
	 * add stock method
	 * @param stockAd - Gets from the user the stock he wants to add to the array.
	 */

	public void addStock (Stock stockAd){
		if(stockAd != null && protfolioSize < MAX_PROTFOLIO_SIZE) {
			if(whereSymbol(stockAd.getSymbol()) == -1){
			this.stocks[protfolioSize] = stockAd;
			protfolioSize++;
			}
			else{
				System.out.println("The stock exists in an array");
			}
		}
		else {
			System.out.println("Can�t add new stock, portfolio can have only" + MAX_PROTFOLIO_SIZE+ "stocks, or null stock");
		}
	}
	
	
	private int whereSymbol (String symbol){
		for(int i=0 ; i < this.protfolioSize ; i++){
			if(symbol.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}
	
	public StockInterface findStockPlace (String stockToFind){
		int i = 0;
		for( i = 0; i< this.protfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return this.stocks[i];
			}
		}
		return null;
	}
	
	/**
	 * remove stock method
	 * @param symbol - Gets from the user the symbol to the stock he wants to remove from the array.
	 */
	public boolean removeStock (String symbol){
		if (whereSymbol(symbol) != -1){
			boolean res = sellStock (symbol, -1);
			if(res = true){
				this.stocks[whereSymbol(symbol)] = this.stocks[protfolioSize-1];
				this.stocks[protfolioSize-1] = null;
				protfolioSize--;
				return true;
			}
		
		}
		return false;
	}
	
	public boolean sellStock (String symbol, int sellQuantity){
		int place = whereSymbol(symbol);
		if (sellQuantity == -1 && place != -1){
			updateBalance(this.stocks[place].getBid() *((Stock) this.stocks[place]).getQuantity());
			((Stock) this.stocks[place]).setQuantity(0);
			System.out.println("All stock is sold");
			return true;
		}
		else if (sellQuantity <= ((Stock) this.stocks[place]).getQuantity() && place != -1 ){
			updateBalance(this.stocks[place].getBid() * sellQuantity);
			((Stock)this.stocks[place]).setQuantity(((Stock)this.stocks[place]).getQuantity() - sellQuantity);
			return true;
		}
		else {
			System.out.println("Not enough stocks to sell OR simbol Doesn't exist in the stock");
			return false;
		}
			
	}
	
	public boolean buyStock (Stock stock, int buyQuantity){
		int howMany = 0;
		int i = whereSymbol(stock.getSymbol());
		
		if(buyQuantity == -1 && i!= -1 ){
			howMany = (int)this.balance/(int)this.stocks[i].getAsk();
			if(this.getBalance() >= howMany * this.stocks[i].getAsk()){
				this.updateBalance(-(howMany * this.stocks[i].getAsk()));
				((Stock)this.stocks[i]).setQuantity(((Stock)this.stocks[i]).getQuantity() + howMany);				
			}
			return true;
		}
		else if (buyQuantity > 0 && i!= -1 && this.getBalance() >= buyQuantity*this.stocks[i].getAsk() ){
			this.updateBalance(-(buyQuantity * this.stocks[i].getAsk()));
			((Stock)this.stocks[i]).setQuantity(((Stock)this.stocks[i]).getQuantity() + buyQuantity );
			return true;
		
		}	
		else if (i == -1){
			addStock(stock);
			System.out.println("The stock was added to the array");
			return false;
		}
		else{
			System.out.println("Not enough balance to complete purchase");
			return false;
			
		}
	}

	public float getStocksValue (){
		float total = 0;
		for(int i =0 ; i < protfolioSize ; i++){
			total = total + this.stocks[i].getBid()*((Stock)this.stocks[i]).getQuantity();
		}
		return total;
	}
	
	public float getTotalValue (){
		float total = 0;
		total = this.getBalance() + this.getStocksValue();
		return total;
	}
	
	
	/**
	 * string method that print to screen
	 * @return string value
	 */
	public String getHtmlString (){
		String ret ="<br><h1>The title is: " + getTitle() + ".</h1></br>";
		
		for(int i=0 ; i < protfolioSize ; i++){
			ret = ret + ((Stock)this.stocks[i]).getHtmlDescription();
		}
		
		ret = ret + "<br><b> Total Portfolio Value:</b> " +getTotalValue() + " $ </br>" + "<br><b> Balance Value: </b>" +getBalance() + " $ </br>" + "<br><b> Stocks Value:</b> " +getStocksValue() + " $ </br>";
		return ret;
	}
}
