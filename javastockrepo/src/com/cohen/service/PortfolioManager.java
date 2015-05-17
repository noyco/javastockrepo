package com.cohen.service;

import com.cohen.model.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * PortfolioManager class 
 * @author noyco
 *
 */
public class PortfolioManager {
	public Portfolio getPortfolio (){
		
		Portfolio myportfolio = new Portfolio ("Exercise 7 portfolio");
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15, 0, 0, 0);
		myportfolio.updateBalance(10000);
		
		Stock s1 = new Stock("PIH",10F,8.5F, cal.getTime());
		myportfolio.addStock(s1);
		Date date2 = cal.getTime();
		
		Stock s2 = new Stock("AAL", 30F, 25.5F, date2);
		myportfolio.addStock(s2);
		
		Date date3 = cal.getTime();
		Stock s3 = new Stock("CAAS", 20F, 15.5F, date3);
		myportfolio.addStock(s3);
		
		myportfolio.buyStock(s1, 20);
		myportfolio.buyStock(s2, 30);
		myportfolio.buyStock(s3, 20);
		
		myportfolio.sellStock("AAL", -1);
		myportfolio.removeStock("CAAS");
		
		return myportfolio;
	}
	
}
	


