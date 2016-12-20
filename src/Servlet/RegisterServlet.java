package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.UserDao;
import OnlineMealOrder.User;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(true);
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String email = request.getParameter("email");
		   
	    UserDao userdao = new UserDao();
	 	if(username.isEmpty()||password.isEmpty()||email.isEmpty())
	 	{
	 		session.setAttribute("RegisterStatus", "fail"); 
	 		response.sendRedirect("Login.jsp");
	 		return;
	 	}
	 	User user = userdao.findUserbyUsername(username);
	 	if( user != null )
	 	{
	 		session.setAttribute("RegisterStatus", "fail"); 
	 		response.sendRedirect("Login.jsp");
	 		return;
	 	}
	 	user = new User(username, password, email);
	 	userdao.createUser(user);
	 	session.setAttribute("RegisterStatus", "success"); 
	 	
	 	 Properties properties = System.getProperties();
		 final String gusername  ="cs5200onlinemeal@gmail.com";  //Your mail id
		 final String gpassword  ="cs5200onlinemeal9";   // Your Password
         Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
 		props.put("mail.smtp.starttls.enable", "true");
 		props.put("mail.smtp.host", "smtp.gmail.com");
 		props.put("mail.smtp.port", "587");
		 Session mailsession = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(gusername, gpassword);
					}
				  });
		 MimeMessage message = new MimeMessage(mailsession);
		 try {
			message.setSubject("Notification emal:A New Register at Spoon&Fork!");
			message.setText("Spoon&Fork Online Meal System");
			message.setFrom(new InternetAddress(user.getEmail()));

			if (user != null){
				
				
					message.addRecipient(Message.RecipientType.TO,
	                    new InternetAddress(user.getEmail()));
				
			}
			Transport.send(message);
	 		response.sendRedirect("Login.jsp");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
