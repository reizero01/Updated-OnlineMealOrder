package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import OnlineMealOrder.Meal;
import OnlineMealOrder.ShoppingCart;

/**
 * Servlet implementation class AddtoShoppingCartServlet
 */
@WebServlet("/AddtoShoppingCartServlet")
public class AddtoShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtoShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String req_quantity = request.getParameter("quantity"); 
   		String req_id = request.getParameter("mealId"); 
   		
   		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
   	 	int i;
   	 	List<Meal> selectMeals = (List<Meal>)session.getAttribute("mealOrder");
   		if (request.getParameter("Add")!= null && req_id != null && !req_quantity.equals("")) { 
    		int quantity = Integer.parseInt(req_quantity);
    		i = Integer.parseInt(req_id);
			for (Meal m: selectMeals)
			{
				if (i == m.getId())
				{
					 cart.addItem(m, quantity); 
					 break;
				}
			}
    	}
   		if(request.getParameter("Remove")!= null && req_id != null)
  	 	{
    		i = Integer.parseInt(req_id);
    		for (Meal m: selectMeals)
			{
				if (i == m.getId())
				{
					 cart.removeItem(m);
					 break;
				}
			}
  	 	}
   		session.setAttribute("cart", cart);
    	response.sendRedirect("ShoppingCart.jsp"); 
   		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
