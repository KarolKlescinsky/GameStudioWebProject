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

		if ("login".equals(action) && request.getParameter("playerName") != null && !new UsefullServicesJpqlMethods()
				.isUserRegistered(request.getParameter("playerName"), request.getParameter("playerPwd"))) {
			System.out.println("podarilo sa");
			session.setAttribute("player", request.getParameter("playerName"));
			session.setAttribute("userLoggedIn", "hidden");
			request.setAttribute("gameName", "login");
		}
		if ("register".equals(action) && !request.getParameter("playerName1").trim().isEmpty()
				&& !request.getParameter("password1").trim().isEmpty()) {
			new UsefullServicesJpqlMethods().playerToDatabaseJpql(request.getParameter("playerName1"),
					request.getParameter("password1"));
			request.setAttribute("isUserRegistred", "hidden");
		} else if ("logout".equals(action)) {
			session.setAttribute("player", null);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
