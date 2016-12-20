package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.FriendGroupDao;
import Dao.UserDao;
import OnlineMealOrder.FriendGroup;
import OnlineMealOrder.User;

/**
 * Servlet implementation class RemoveGroupServlet
 */
@WebServlet("/RemoveGroupServlet")
public class RemoveGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String friendgroup = request.getParameter("friendgroup");
		int groupId = Integer.parseInt(friendgroup);
		//User user = (User)session.getAttribute("User");
		FriendGroupDao fgdao = new FriendGroupDao();
	    fgdao.removeGroup(groupId);
	    session.setAttribute("RemoveGroupStatus","succeed") ;
    	response.sendRedirect("CreateGroup.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
