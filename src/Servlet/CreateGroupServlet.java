package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import Dao.FriendGroupDao;
import Dao.UserDao;
import OnlineMealOrder.User;

/**
 * Servlet implementation class CreateGroupServlet
 */
@WebServlet("/CreateGroupServlet")
public class CreateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String groupname = request.getParameter("groupname");
		User user = (User)session.getAttribute("User");
		
	    FriendGroupDao fgdao = new FriendGroupDao();
	    if(groupname.isEmpty())
	    {
	    	session.setAttribute("AddGroupStatus","fail") ;
	    	response.sendRedirect("CreateGroup.jsp");
	    	return;
	    }
	    if(fgdao.findGroupbyNameandCreator(user,groupname) != null)
	    {
	    	session.setAttribute("AddGroupStatus","fail") ;
	    	response.sendRedirect("CreateGroup.jsp");
	    	return;
	    }
	    session.setAttribute("AddGroupStatus","succeed") ;
	    fgdao.createGroup(user, groupname);
    	response.sendRedirect("CreateGroup.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
