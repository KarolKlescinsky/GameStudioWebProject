package sk.tsystems;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.servicesjpql.CommentJpqlMethods;
import services.servicesjpql.RatingJpqlMethods;
import services.servicesjpql.ScoreJpqlMethods;
import services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;
import sk.tsystems.coreStones.FieldStones;

/**
 * Servlet implementation class StonesServlet
 */
@WebServlet("/Stones")
public class StonesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long startTime = System.currentTimeMillis();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StonesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		HttpSession session = request.getSession();

		FieldStones fieldStones = (FieldStones) session.getAttribute("fieldStones");
		if (fieldStones == null || request.getParameter("newGame") != null) {
			fieldStones = new FieldStones(4, 4);
			startTime = System.currentTimeMillis();
			session.setAttribute("fieldStones", fieldStones);
		}

		try {
			int value = Integer.parseInt(request.getParameter("value"));
			fieldStones.move(value);
		} catch (Exception e) {
		}

		String command = request.getParameter("command");
		if (command != null) {
			switch (command) {
			case "up":
				fieldStones.moveUp();
				break;
			case "down":
				fieldStones.moveDown();
				break;
			case "left":
				fieldStones.moveLeft();
				break;
			case "right":
				fieldStones.moveRight();
				break;
			}
		}

		String gameName = "Stones";

		out.print("<div align=\"center\">");

		out.println("<table border=\"1\" id=\"Stones\"> ");
		// align=\"center\"
		for (int row = 0; row < fieldStones.getRowCount(); row++) {
			out.println("<tr>");
			for (int column = 0; column < fieldStones.getColumnCount(); column++) {
				out.println("<td>");
				int value = fieldStones.getValueAt(row, column);
				if (value == FieldStones.EMPTY_CELL) {
					out.printf(" ");
				} else {
					out.printf("%2d", value, value);
				}
			}
		}

		out.println("</table>");

		out.println("<br>");

		out.println("<form id=\"restart\">");
		out.printf("<a class=\"restartGame\" href=\"?gameName=%s&newGame=%s\" align=\"center\">Restart Game</a>",
				gameName, "NewGame");
		out.println("</form>");
		out.println("<br>");

		out.println("<br>");

		out.print("<div id=\"arrow\">");
		out.printf(
				"<a href=\"?gameName=%s&command=up\"><img src=\"image/arrowup.png\" alt=\"UP\"style=\"width:60px;height:60px;\"></a>",
				gameName);
		out.print("</div>");
		out.print("<div id=\"arrow1\">");
		out.printf(
				"<a href=\"?gameName=%s&command=left\"><img src=\"image/arrowleft.png\" alt=\"LEFT\"style=\"width:60px;height:60px;\"></a>",
				gameName);
		out.printf(
				"<a href=\"?gameName=%s&command=down\"><img src=\"image/arrowdown.png\" alt=\"DOWN\"style=\"width:60px;height:60px;\"></a>",
				gameName);
		out.printf(
				"<a href=\"?gameName=%s&command=right\"><img src=\"image/arrowright.png\" alt=\"RIGHT\"style=\"width:60px;height:60px;\"></a><br>",
				gameName);
		out.print("</div><br>");

		if (fieldStones.isSolved()) {

			out.println("<img src=\"image/win3.gif\" alt=\"Win\" align=\"center\">");
			out.println("<br>");
			out.println("<h2>");
			out.println("Congratulations, you won the Game!");
			out.println("</h2>");
			out.print("</div>");

			String userName = (String) session.getAttribute("player");
			int playersScore = (int) (1000 - (System.currentTimeMillis() - startTime) / 1000);

			out.print("<div id=\"textMinesweeper\">");

			if (session.getAttribute("player") == null) {
				out.println("You have to log in to comment/rate game.");
				// out.println("<form>");
				// out.printf("<input type=\"hidden\" name=\"gameName\"
				// value=\"%s\"><br>", gameName);
				// out.println("Write down your name:<input type=\"text\"
				// name=\"userName\"><br>");
				// out.println("<input type=\"submit\"><br>");
				// out.println("</form>");
			} else {
				new ScoreJpqlMethods().scoreToDatabaseJPQL(playersScore, gameName, userName);

				request.getParameter("playersComment");
				out.println("<form>");
				out.printf("<input type=\"hidden\" name=\"gameName\" value=\"%s\"><br>", gameName);
				out.printf("<input type=\"hidden\" name=\"userName\" value=\"%s\"><br>", userName);
				out.println("Write down your comment:<input type=\"text\" name=\"playersComment\"><br>");
				out.println("<input type=\"submit\"><br>");
				out.println("</form>");
				request.getParameter("playersRating");
				out.println("<form>");
				out.printf("<input type=\"hidden\" name=\"gameName\" value=\"%s\"><br>", gameName);
				out.printf("<input type=\"hidden\" name=\"userName\" value=\"%s\"><br>", userName);
				out.println("Write down your rating:<input type=\"text\" name=\"playersRating\"><br>");
				out.println("<input type=\"submit\"><br>");
				out.println("</form>");

				if (request.getParameter("playersComment") != null) {
					new CommentJpqlMethods().commentToDatabaseJPQL(request.getParameter("playersComment"), gameName,
							userName);
				}

				if (request.getParameter("playersRating") != null) {
					new RatingJpqlMethods().addUniqueRatingToDatabase(
							Integer.parseInt(request.getParameter("playersRating")), gameName, userName);
				}
			}
		}
		out.print("</div>");

		out.print("<div id=\"commentM\">");

		request.setAttribute("Comments",
				new CommentJpqlMethods().printComment(new UsefullServicesJpqlMethods().findGameObjectbyID("Stones")));
		out.print("</div>");

		out.print("<div id=\"commentM\">");
		request.setAttribute("Scores",
				new ScoreJpqlMethods().printScore(new UsefullServicesJpqlMethods().findGameObjectbyID("Stones")));
		out.print("</div>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
