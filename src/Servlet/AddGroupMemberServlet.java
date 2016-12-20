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
 * Servlet implementation class AddGroupMemberServlet
 */
@WebServlet("/AddGroupMemberServlet")
public class AddGroupMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGroupMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String groupname = request.getParameter("groupname");
		String membername = request.getParameter("membername");
		User user = (User)session.getAttribute("User");
		
	    FriendGroupDao fgdao = new FriendGroupDao();
	    UserDao usrdao = new UserDao();
	    if(groupname.isEmpty()||membername.isEmpty())
	    {
	    	session.setAttribute("AddMemberStatus","fail") ;
	    	response.sendRedirect("CreateGroup.jsp");
	    	return;
	    }
	    User member = usrdao.findUserbyUsername(membername);
	    FriendGroup friendgroup = fgdao.findGroupbyNameandCreator(user, groupname);
	    if(friendgroup == null || member == null )
	    {
	    	session.setAttribute("AddMemberStatus","fail") ;
	    	response.sendRedirect("CreateGroup.jsp");
	    	return;
	    }
	    if(fgdao.findGroupMember(friendgroup, membername) != null)
	    {
	    	session.setAttribute("AddMemberStatus","fail") ;
	    	response.sendRedirect("CreateGroup.jsp");
	    	return;
	    }
	 
	    fgdao.addGroupMember(friendgroup, member);
	    session.setAttribute("AddMemberStatus","succeed") ;
    	response.sendRedirect("CreateGroup.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
