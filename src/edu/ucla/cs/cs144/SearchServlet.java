package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter; // print
import edu.ucla.cs.cs144.AuctionSearchClient;
import edu.ucla.cs.cs144.SearchResult;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // your codes here
		response.setContentType("text/html");
		
		String query = request.getParameter("q");
		// out.println("query: " + query);

		int numToSkip = Integer.parseInt(request.getParameter("numResultsToSkip"));
		// out.println("numResultsToSkip: " + numToSkip);

		int numToReturn = Integer.parseInt(request.getParameter("numResultsToReturn"));
		// request.setAttribute("numResultsToReturn", numToReturn);
		// out.println("numResultsToReturn: " + numToReturn);

		AuctionSearchClient auctionSearch = new AuctionSearchClient();
		SearchResult[] searchResult = auctionSearch.basicSearch(query, numToSkip, numToReturn);
		// String[] xmlFormattedResult = new String[searchResult.length]; 
		String[] ids = new String[searchResult.length];
		String[] names = new String[searchResult.length]; 

		for (int i = 0; i < searchResult.length; i++) {
			ids[i] = searchResult[i].getItemId();
			names[i] = searchResult[i].getName();
			// xmlFormattedResult[i] = auctionSearch.getXMLDataForItemId(searchResult[i].getItemId());
			// out.println(xmlFormattedResult[i]);
		}


		request.setAttribute("idList", ids);
		request.setAttribute("nameList", names);
		

		request.getRequestDispatcher("/searchResult.jsp").forward(request, response);
		// Print the HTML footer
		// out.println("</BODY></HTML>");
		// out.close();
    }
}
