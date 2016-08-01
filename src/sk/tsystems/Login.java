package sk.tsystems;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session = request.getSession();
																																	
		if (session.getAttribute("userName") == null) {
			request.setAttribute("userName", true);
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


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
