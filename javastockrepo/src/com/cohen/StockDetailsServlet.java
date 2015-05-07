package com.cohen;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15, 0, 0, 0);
		
		Date pihDate = cal.getTime();
		Date aalDate = cal.getTime();
		Date caasDate = cal.getTime();
		
		float pihAsk = 13.1f;
		float aalAsk = 5.78f;
		float caasAsk = 32.2f;
		
		float pihBid = 12.4f;
		float aalBid = 5.5f;
		float caasBid = 31.5f;

	
		Stock pihStock1 = new Stock ("PIH", pihAsk , pihBid , pihDate, 0, 0);
		Stock aalStock2 = new Stock ("AAL", aalAsk , aalBid , aalDate, 0, 0);
		Stock caasStock3 = new Stock ("CAAS",caasAsk , caasBid , caasDate, 0, 0);
		
		resp.getWriter().println(pihStock1.getHtmlDescription());
		resp.getWriter().println(aalStock2.getHtmlDescription());
		resp.getWriter().println(caasStock3.getHtmlDescription());
	}
}
