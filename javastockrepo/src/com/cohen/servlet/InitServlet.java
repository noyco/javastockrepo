package com.cohen.servlet;

import javax.servlet.ServletException;
import org.algo.service.ServiceManager;
import com.cohen.service.*;

@SuppressWarnings("serial")
public class InitServlet extends javax.servlet.http.HttpServlet {
	
@Override

	public void init() throws ServletException {
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);
	}


}




