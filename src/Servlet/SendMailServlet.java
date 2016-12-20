package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.FriendGroupDao;
import OnlineMealOrder.FriendGroup;
import OnlineMealOrder.User;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Servlet implementation class SendMailServlet
 */
@WebServlet("/SendMailServlet")
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<User>memberList = (List<User>)session.getAttribute("memberList");
		User user = (User)session.getAttribute("User");
		 Properties properties = System.getProperties();
		 final String username  ="spoonforkcs5200@gmail.com";  //Your mail id
		 final String password  ="xujics5200";   // Your Password
         Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
 		props.put("mail.smtp.starttls.enable", "true");
 		props.put("mail.smtp.host", "smtp.gmail.com");
 		props.put("mail.smtp.port", "587");
		 Session mailsession = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		 MimeMessage message = new MimeMessage(mailsession);
		 try {
			message.setSubject("Notification emal:A New Order at Spoon&Fork!");
			message.setText("Spoon&Fork Online Meal System");
			message.setFrom(new InternetAddress(username));
			message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(user.getEmail()));
			if (memberList != null){
				for(User u: memberList)
				{
					message.addRecipient(Message.RecipientType.TO,
	                    new InternetAddress(u.getEmail()));
				}
			}
			Transport.send(message);
	 		response.sendRedirect("SendEmail.jsp");
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
