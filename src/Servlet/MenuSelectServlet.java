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

import OnlineMealOrder.Meal;
import OnlineMealOrder.MealClient;
import OnlineMealOrder.Restaurant;

/**
 * Servlet implementation class MenuSelectServlet
 */
@WebServlet("/MenuSelectServlet")
public class MenuSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String[] rst = request.getParameterValues("RestaurantId");
		if (rst == null)
		{
			response.sendRedirect("RestaurantSelect.jsp"); 
			return;
		}
		List<Meal> meals = new ArrayList<Meal>();	
		List<Restaurant> restaurants = (List<Restaurant>)session.getAttribute("restaurantList");
		int[] rstId = new int[rst.length];
		for(int i=0; i<rst.length; i++)
			rstId[i] = Integer.parseInt(rst[i]);

	   	MealClient client = new MealClient();
	    for(Restaurant r: restaurants)
	    {
	    	for(int id: rstId)
	    	{
	    		if(id == r.getId())
	    		{
	    			List<Meal> newMeal = client.search(id);
	    			for(Meal m: newMeal )
	    			{
						m.setRestaurant(r);
	    			}
	    			meals.addAll(newMeal);
	    		}
	    	}
	    }
	   	session.setAttribute("mealList",meals);
	   	response.sendRedirect("MenuSelect.jsp"); 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
