package com.cohen.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cohen.model.Portfolio;
import com.cohen.model.Stock;
import com.cohen.service.PortfolioManager;

/**
 * PortfolioServlet class extends from HttpServlet
 * @author noyco
 *
 */
public class PortfolioServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio = portfolioManager.getPortfolio();
		resp.getWriter().println(portfolio.getHtmlString());
		
		Portfolio portfolio2 = new Portfolio (portfolio);
		portfolio2.setTitle("portfolio #2");
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio.removeStock("PIH");
		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		Stock [] a = portfolio2.getStocks();
		a[portfolio2.getProtfolioSize()-1].setBid(55.55f);
		resp.getWriter().println(portfolio.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		
		
	}

}
