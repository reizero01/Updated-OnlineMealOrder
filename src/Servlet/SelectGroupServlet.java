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

import Dao.FriendGroupDao;
import OnlineMealOrder.FriendGroup;
import OnlineMealOrder.User;

/**
 * Servlet implementation class SelectGroupServlet
 */
@WebServlet("/SelectGroupServlet")
public class SelectGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("User");
		FriendGroupDao fgDao = new FriendGroupDao();
		List<FriendGroup>groupList = new ArrayList<FriendGroup>();	
		List<User>memberList = new ArrayList<User>();	
		groupList = fgDao.findGroupbyCreator(user);
		session.setAttribute("groupList", groupList);
		String selectgroup = request.getParameter("selectgroup");
		if(selectgroup == null)
		{
			response.sendRedirect("GroupSelect.jsp");
			return;
		}
		int groupId = Integer.parseInt(selectgroup);
		FriendGroupDao fgdao = new FriendGroupDao();
		memberList = fgdao.findGroupMembersbyId(groupId);
	    session.setAttribute("memberList", memberList);
    	response.sendRedirect("GroupSelect.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
