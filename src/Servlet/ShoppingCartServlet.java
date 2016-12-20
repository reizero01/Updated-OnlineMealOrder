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

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		String[] ml = request.getParameterValues("mId");
		if (ml == null)
		{
			response.sendRedirect("MenuSelect.jsp"); 
			return;
		}
		List<Meal> newMeals = new ArrayList<Meal>();
		List<Meal> meals = (List<Meal>)request.getSession().getAttribute("mealList");
		int[] mlId = new int[ml.length];
		for(int i=0; i<ml.length; i++)
			mlId[i] = Integer.parseInt(ml[i]);
   		for(Meal m: meals)
   	 	{
    		for(int id: mlId)
    		{	
    			if(id == m.getId())
    			{
    				newMeals.add(m);
    				break;
    			}
    		}
    	}
    	session.setAttribute("mealOrder", newMeals);
    	response.sendRedirect("ShoppingCart.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
