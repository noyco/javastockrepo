package com.cohen.model;

import java.util.Date;

import com.cohen.exception.*;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

/**
 * represents a Portfolio of Stocks.
 * where the maximum of stocks in the Portfolio is 5.
 * 
 * @author noyco.
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
	 * Creates an instance of an array of Stocks
	 * Set the Portfolio Size to start as 0.
	 */
	public Portfolio () {
		this.title =new String ("temp title");
		this.stocks = new Stock [MAX_PROTFOLIO_SIZE];
		this.protfolioSize = 0;
		this.balance = 0;
	}
	
	/**
	 * Copy c'tor of portfolio type
	 * copy an array of stocks from one to the empty one
	 * @param portfolio
	 * @throws BalanceException 
	 */
	public Portfolio(Portfolio portfolioToCopy) throws BalanceException {
		this.title = portfolioToCopy.getTitle();
		for (int i=0; i < portfolioToCopy.getProtfolioSize(); i++)
		{
			this.stocks[i] = new Stock((Stock) portfolioToCopy.getStocks()[i]);
		}
		this.protfolioSize = portfolioToCopy.getProtfolioSize();
	}
	
	/**
	 * C'tor of Portfolio.
	 * Creates an instance of an array of Stocks
	 * Set the Portfolio Size to start as 0.
	 * @param stocksArray
	 */
	
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
	
	public void updateBalance (float amount) throws BalanceException{
		if(this.balance + amount < 0){
			throw new BalanceException("You cant go into overdraft");	
		}
		else {
			this.balance += amount;	
		}
	}
	
	/**
	 * add stock method
	 * @param stockAd - Gets from the user the stock he wants to add to the array.
	 * @throws StockAlreadyExistsException, PortfolioFullException
	 */
	public void addStock (Stock stockAd) throws StockAlreadyExistsException, PortfolioFullException {
		if(stockAd != null && protfolioSize < MAX_PROTFOLIO_SIZE) {
			if(whereSymbol(stockAd.getSymbol()) == -1){
			this.stocks[protfolioSize] = stockAd;
			protfolioSize++;
			}
			else{
				throw new StockAlreadyExistsException(stockAd.getSymbol());
			}
		}
		else {
			throw new PortfolioFullException();
		}
	}

	
	/**
	 * Find a stock in stocks array by symbol
	 * @param symbol
	 * @return index of the stock in the stocks array
	 * or return -1 if stock not found
	 */
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
	 * remove stock method: remove stocks from protfolio , by comparison the symbol of the stock
	 * @param symbol - Gets from the user the symbol to the stock he wants to remove from the array.
	 * @throws BalanceException, StockNotExistException
	 */
	public void removeStock (String symbol) throws StockNotExistException, BalanceException{
		if (whereSymbol(symbol) != -1){
			sellStock (symbol, -1);
			this.stocks[whereSymbol(symbol)] = this.stocks[protfolioSize-1];
			this.stocks[protfolioSize-1] = null;
			protfolioSize--;
				
		}
		else{
			throw new StockNotExistException();
		}
		
	}
	/**
	 * Method update stock quantity Depending sale
	 * "-1 " means all specific stocks will be sold
	 * @param symbol, sellQuantity
	 * @throws BalanceException, IllegalArgumentException, StockNotExistException
	 */
	public void sellStock (String symbol, int sellQuantity) throws BalanceException, IllegalArgumentException, StockNotExistException{
		int place = whereSymbol(symbol);
		if (symbol == null || sellQuantity < -1 || sellQuantity == 0 )
		{
			throw new IllegalArgumentException("Check your stock symbol or stock quntity.");
		}
		if (sellQuantity == -1 && place != -1){
			updateBalance(this.stocks[place].getBid() *((Stock) this.stocks[place]).getQuantity());
			((Stock) this.stocks[place]).setQuantity(0);
			System.out.println("All stock is sold");
			
		}
		else if (sellQuantity <= ((Stock) this.stocks[place]).getQuantity() && place != -1 ){
			updateBalance(this.stocks[place].getBid() * sellQuantity);
			((Stock)this.stocks[place]).setQuantity(((Stock)this.stocks[place]).getQuantity() - sellQuantity);
			System.out.println(sellQuantity +" stocks of "+ symbol + " was sold succefully");
			
		}
		else if (place == -1){
			throw new StockNotExistException(symbol);
		}
		else {
			throw new IllegalArgumentException("Not enough stocks to sell");
			
		}
			
	}
	/**
	 * Method update the stock quantity depending Buy
	 * " -1" means all specific stocks will be used
	 * @param stock, buyQuantity
	 * @throws BalanceException, PortfolioFullException, StockAlreadyExistsException, StockNotExistException
	 */
	public void buyStock (Stock stock, int buyQuantity) throws BalanceException, PortfolioFullException, StockAlreadyExistsException, StockNotExistException {
		int howMany = 0;
		int i = whereSymbol(stock.getSymbol());
		
		if(buyQuantity == -1){
			howMany = (int)this.balance/(int)this.stocks[i].getAsk();
			if(this.getBalance() >= howMany * this.stocks[i].getAsk()){
				this.updateBalance(-(howMany * this.stocks[i].getAsk()));
				((Stock)this.stocks[i]).setQuantity(((Stock)this.stocks[i]).getQuantity() + howMany);				
			}
		}
		else if (buyQuantity > 0 && this.getBalance() >= buyQuantity*this.stocks[i].getAsk() ){
			this.updateBalance(-(buyQuantity * this.stocks[i].getAsk()));
			((Stock)this.stocks[i]).setQuantity(((Stock)this.stocks[i]).getQuantity() + buyQuantity );
		
		}	
		else if (i == -1){
			addStock(stock);
			System.out.println("The stock was added to the array");
		}
		else{
			throw new BalanceException();
			//throw new BalanceException("Not enough balance to complete purchase");
			
			
		}
	}
	/**
	 * calculates the portfolio's total stocks value.
	 * @return float
	 */
	public float getStocksValue (){
		float total = 0;
		for(int i =0 ; i < protfolioSize ; i++){
			total = total + this.stocks[i].getBid()*((Stock)this.stocks[i]).getQuantity();
		}
		return total;
	}
	
	/**
	 * Method calculates the portfolio's total value by using another methods.
	 * @return float
	 */
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
