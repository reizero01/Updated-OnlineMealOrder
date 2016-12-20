package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import OnlineMealOrder.Restaurant;
import OnlineMealOrder.RestaurantClient;

/**
 * Servlet implementation class RestaurantSearchServlet
 */
@WebServlet("/RestaurantSearchServlet")
public class RestaurantSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestaurantSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String address=request.getParameter("address"); 
		String city=request.getParameter("city"); 
		String zip=request.getParameter("zip");
		String cuisine=request.getParameter("cuisine");
		if(address == null || city == null || zip == null)
		{
			response.sendRedirect("RestaurantSearch.jsp"); 
			return;
		}
		
		if(address.isEmpty() || city.isEmpty() || zip.isEmpty())
		{
			response.sendRedirect("RestaurantSearch.jsp"); 
			return;
		}
		RestaurantClient client = new RestaurantClient();
		
		List<Restaurant> restaurants = client.search(zip, city, address);
		List<Restaurant> newRestaurants = new ArrayList<Restaurant>();
		if (restaurants == null)
		{
			response.sendRedirect("RestaurantSearch.jsp"); 
			return;
		}
		for(Restaurant r: restaurants)
		{
			List<String> cu = r.getCuisine();
			for(String s: cu)
			{
				if(s.equals(cuisine))
				{
					newRestaurants.add(r);
					break;
				}
			}
		}
		session.setAttribute("restaurantList",newRestaurants);
		response.sendRedirect("RestaurantSelect.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
