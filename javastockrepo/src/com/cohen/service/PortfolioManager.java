package com.cohen.service;

import com.cohen.Stock;
import com.cohen.model.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PortfolioManager {
	public Portfolio getPortfolio (){
		
		Portfolio portfolio = new Portfolio ();
		portfolio.setTitle("portfolio");
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15, 0, 0, 0);
		
		Stock pihStock1 = new Stock ("PIH", 13.1f , 12.4f , cal.getTime(), 0, 0);
		portfolio.addStock(pihStock1);
		Stock aalStock2 = new Stock ("AAL", 5.78f ,  5.5f , cal.getTime(), 0, 0);
		portfolio.addStock(aalStock2);
		Stock caasStock3 = new Stock ("CAAS",32.2f , 31.5f , cal.getTime(), 0, 0);
		portfolio.addStock(caasStock3);
		
		return portfolio;
	}
	
}
	


