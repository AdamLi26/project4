package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter; // print
import edu.ucla.cs.cs144.AuctionSearchClient;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
        AuctionSearchClient auctionSearch = new AuctionSearchClient();
        String xmlFormattedResult = auctionSearch.getXMLDataForItemId(request.getParameter("itemId")); 
		    
        request.setAttribute("dump", xmlFormattedResult);
        request.getRequestDispatcher("itemDetail.jsp").forward(request, response);
    }
}
