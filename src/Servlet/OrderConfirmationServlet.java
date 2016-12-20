package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CheckoutDao;
import Dao.MealDao;
import Dao.RestaurantDao;
import OnlineMealOrder.CartItem;
import OnlineMealOrder.Checkout;
import OnlineMealOrder.Meal;
import OnlineMealOrder.Orders;
import OnlineMealOrder.Restaurant;
import OnlineMealOrder.ShoppingCart;
import OnlineMealOrder.User;

/**
 * Servlet implementation class OrderConfirmationServlet
 */
@WebServlet("/OrderConfirmationServlet")
public class OrderConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderConfirmationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		if(!cart.getEnumeration().hasMoreElements())
		{
			response.sendRedirect("ShoppingCart.jsp");
			return;
		}
		User user = (User)session.getAttribute("User");
		Enumeration<CartItem> allItems = cart.getEnumeration();
		CartItem tmpItem; 
		double totalCost = 0.00;
		Checkout ck = new Checkout();
		List<Orders> orderList = new ArrayList<Orders>();
		ck.setOrders(orderList);
		ck.setUser(user);
		ck.setCheckDate(new Timestamp(System.currentTimeMillis()));
		Orders od = null; 
		Meal ml = null;
		Restaurant rst = null;
		MealDao mlDao = new MealDao();
		RestaurantDao rstDao = new RestaurantDao();
	 	while (allItems.hasMoreElements()) { 
	 		tmpItem = (CartItem)allItems.nextElement();
	 		od = new Orders();
	 		rst = tmpItem.getMeal().getRestaurant();
	 		rstDao.createRestaurant(rst);
	 		ml = tmpItem.getMeal();
	 		mlDao.createMeal(ml);
	 		od.setUser(user);
	 		od.setMeal(tmpItem.getMeal());
	 		od.setQuantity(tmpItem.getQuantity());
	 		od.setCheckout(ck);
	 		orderList.add(od);
	 		totalCost += tmpItem.getQuantity()*tmpItem.getMeal().getPrice();
	 	}
	 	ck.setTotalCost(totalCost);
	 	CheckoutDao ckDao = new CheckoutDao();
	 	ckDao.createCheckout(ck);
		session.setAttribute("cart", null);
	 	response.sendRedirect("SelectGroupServlet"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
