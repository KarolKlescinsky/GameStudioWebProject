package sk.tsystems;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();
			
		String action = request.getParameter("action");

//		
//		if ("login".equals(action) && "userName" != null && "userPassword" != null) {
//
//			user = new UsefullServicesJpqlMethods()
//			session = request.getSession();
//			session.setAttribute("user", user);
//			
//		} else  if("registration".equals(action) && "userName" != null && "userPassword" != null) {
//			
//		}
//
//		if (request.getParameter("playerName")!=null) {
//			session.setAttribute("playerName", request.getParameter("playerName"));
//			new UsefullServicesJpqlMethods().playerToDatabaseJpql(request.getParameter("playerName"), request.getParameter("password1"));
//			session.setAttribute("isUserRegistred", "hidden");
//		}
		
		if(new UsefullServicesJpqlMethods().isUserRegistered(request.getParameter("playerName"), request.getParameter("password1"))){
			
		} else {
			
		}
		
		
	//	request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

//			out.print("<div align=\"center\">");
//			out.println("You have to log in to comment/rate game.");
//			out.println("<form>");
//			out.printf("<input type=\"hidden\" name=\"gameName\" value=\"%s\"><br>", log);
//			out.println("Write down your name:<input type=\"text\" name=\"userName\">");
//			out.println("<input type=\"submit\"><br>");
//			out.println("</form><br>");
//			out.print("</div>");


		
//		 if(checkPlayerName(request, request.getParameter("playerName"))&& checkPlayerPassword(request, request.getParameter("password1"))){
//				
//				
//				if(new PlayerServiceJPQL().checkExistPlayer(request.getParameter("playerName"))){
//				request.setAttribute("checkName", true);	

//				request.setAttribute("login", session.getAttribute("name"));
//				request.setAttribute("showForm", true);
//				}
//				else{
//					request.setAttribute("checkName", true);		
//				}
//				
//			//	new PlayerServiceJPQL().addPlayer(request.getParameter("playerName"), request.getParameter("password1"));
//			}
		

	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

	

}
