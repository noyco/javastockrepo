package com.cohen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex3Servlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		
		int radius = 50;
		int base = 20;
		int exp = 13;
		int hypotenuse = 50; 
		int angle = 30;
		
		double calculation1 = Math.PI * Math.pow(radius, 2);
		double calculation2 = Math.sin(angle*Math.PI/180) * hypotenuse;
		double calculation3 = Math.pow(base, exp);
		
		String line1 ="calculation 1: Area of circle with radius " + radius+ " is :" + calculation1+ "";
		String line2 ="calculation 2: Length of opposite where angle B is "+angle+ " degrees and Hypotenuse length is " +hypotenuse+ " cm is: "+calculation2+" cm"+"";
		String line3 ="calculation 3: Power of " +base+ " with exp of " +exp+" is :" + calculation3+ "";
		
		String resultStr = line1 + "<br>" +line2 + "<br>" + line3;
		
		resp.getWriter().println(resultStr);
		
	}
}