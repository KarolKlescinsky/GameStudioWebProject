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
import sk.tsystems.core.Field;

import usefullmethods.UserLogin;

/**
 * Servlet implementation class Mines
 */
@WebServlet("/Minesweeper")
public class MinesweeperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long startTime = System.currentTimeMillis();

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public MinesweeperServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		boolean mineBoomed = false;

		HttpSession session = request.getSession();
		Field field = (Field) session.getAttribute("field");

		if (field == null || request.getParameter("newGame") != null) {
			field = new Field(10, 10, 5);
			session.setAttribute("field", field);
		}

		try {
			int stlpec = Integer.parseInt(request.getParameter("stlpec"));
			int riadok = Integer.parseInt(request.getParameter("riadok"));
			int status = Integer.parseInt(request.getParameter("status"));

			if (status == 0) {
				field.openTile(riadok, stlpec);
			} else {
				if (status == 1) {
					field.markTile(riadok, stlpec);
				} else {
					field.markTile(riadok, stlpec);
				}
			}
		} catch (Exception e) {
		}

		String gameName = "Minesweeper";

		out.print("<div align=\"center\">");

		out.println("<table border=\"1\" align=\"center\">");

		for (int riadok = 0; riadok < field.getRowCount(); riadok++) {
			out.println("<tr>");

			for (int stlpec = 0; stlpec < field.getRowCount(); stlpec++) {

				if ((field.getTile(riadok, stlpec).getState()) == (field.getTile(riadok, stlpec).getState().OPEN)) {
					if (field.getTile(riadok, stlpec) instanceof sk.tsystems.core.Mine) {
						out.printf("<td><img src=\"image/mine.png\" alt=\"Mine\"style=\"width:30px;height:30px;\">");
						// out.println("Boom! You lost!");
						mineBoomed = true;

					} else if (field.getTile(riadok, stlpec) instanceof sk.tsystems.core.Clue) {

						switch (((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue()) {
						case 0:
							out.printf(
									"<td><img src=\"image/zero.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						case 1:
							out.printf("<td><img src=\"image/one.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						case 2:
							out.printf("<td><img src=\"image/two.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						case 3:
							out.printf(
									"<td><img src=\"image/three.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						case 4:
							out.printf(
									"<td><img src=\"image/four.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						case 5:
							out.printf(
									"<td><img src=\"image/five.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						case 6:
							out.printf("<td><img src=\"image/six.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						case 7:
							out.printf(
									"<td><img src=\"image/seven.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						case 8:
							out.printf(
									"<td><img src=\"image/eight.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						default:
							out.printf(
									"<td><img src=\"image/zero.jpg\" alt=\"zero\" style=\"width:30px;height:30px;\">",
									((sk.tsystems.core.Clue) field.getTile(riadok, stlpec)).getValue());
							break;
						}
					}
				}

				else if ((field.getTile(riadok, stlpec)
						.getState()) == (field.getTile(riadok, stlpec).getState().CLOSED)) {
					// out.printf(
					// "<td><a href=\"?stlpec=%d&riadok=%d\"><img
					// src=\"image/otaznik.jpg\"
					// alt=\"Mystery\"style=\"width:30px;height:30px;\"></a>",
					// stlpec, riadok);
					out.printf("<td><a href=\"?gameName=%s&stlpec=%d&riadok=%d&status=%d\">O</a>", gameName, stlpec,
							riadok, 0);
					out.printf(" ");
					out.printf("<a href=\"?gameName=%s&stlpec=%d&riadok=%d&status=%d\">M</a>", gameName, stlpec, riadok,
							1);

				}

				else if ((field.getTile(riadok, stlpec)
						.getState()) == (field.getTile(riadok, stlpec).getState().MARKED)) {
					out.printf(
							"<td><a href=\"?gameName=%s&stlpec=%d&riadok=%d&status=%d\"><img src=\"image/flag.png\" alt=\"Marked\"style=\"width:30px;height:30px;\"></a>",
							gameName, stlpec, riadok, 3);
				}
			}
		}

		if (mineBoomed) {
			out.println("<script>");
			out.println("alert(\"You lost! \");");
			out.println("</script>");

			field = new Field(10, 10, 10);
			session.setAttribute("field", field);
		}
		out.println("</table>");
		out.println("<br>");

		out.println("<form id=\"restart\">");
		out.printf("<a class=\"restartGame\" href=\"?gameName=%s&newGame=%s\" align=\"center\">Restart Game</a>",
				gameName, "NewGame");
		out.println("</form>");
		out.println("<br>");

		if (field.isSolved()) {

			out.println("<img src=\"image/win.gif\" alt=\"Win\" align=\"center\">");
			out.println("<br>");
			out.println("<h2>");
			out.println("Congratulations, you won the Game!");
			out.println("</h2>");
			out.print("</div>");
			out.print("<div id=\"textMinesweeper\">");

			int playersScore = (int) (1000 - (System.currentTimeMillis() - startTime) / 1000);
			String userName = (String) session.getAttribute("player");

			if (session.getAttribute("player") == null) {
				out.println("You have to log in to comment/rate game.");
				// out.println("<form>");
				// out.printf("<input type=\"hidden\" name=\"gameName\"
				// value=\"%s\"><br>", gameName);
				// out.println("Write down your name:<input type=\"text\"
				// name=\"userName\">");
				// out.println("<input type=\"submit\"><br>");
				// out.println("</form>");
			} else {
				new ScoreJpqlMethods().scoreToDatabaseJPQL(playersScore, gameName, userName);

				out.println("Congratulations. You successfully loggedn in.");
				out.println("<br>");
				out.println("Now you can comment and rate this game.");

				request.getParameter("playersComment");
				out.println("<form>");
				out.printf("<input type=\"hidden\" name=\"gameName\" value=\"%s\"><br>", gameName);
				out.printf("<input type=\"hidden\" name=\"userName\" value=\"%s\"><br>", userName);
				out.println("Write down your comment:<input type=\"text\" name=\"playersComment\">");
				out.println("<input type=\"submit\"><br>");
				out.println("</form>");
				request.getParameter("playersRating");
				out.println("<form>");
				out.printf("<input type=\"hidden\" name=\"gameName\" value=\"%s\"><br>", gameName);
				out.printf("<input type=\"hidden\" name=\"userName\" value=\"%s\"><br>", userName);
				out.println("Write down your rating:<input type=\"text\" name=\"playersRating\">");
				out.println("<input type=\"submit\"><br>");
				out.println("</form>");

				out.print("<br>");
				// out.print("<div id=\"commentM\">");

				// out.print("</div>");

				if (request.getParameter("playersComment") != null) {
					new CommentJpqlMethods().commentToDatabaseJPQL(request.getParameter("playersComment"), gameName,
							userName);
				}

				if (request.getParameter("playersRating") != null) {
					new RatingJpqlMethods().addUniqueRatingToDatabase(
							Integer.parseInt(request.getParameter("playersRating")), gameName, userName);
				}
			}
			out.print("</div>");
		}

		request.setAttribute("Comments", new CommentJpqlMethods()
				.printComment(new UsefullServicesJpqlMethods().findGameObjectbyID("Minesweeper")));

		request.setAttribute("Scores",
				new ScoreJpqlMethods().printScore(new UsefullServicesJpqlMethods().findGameObjectbyID("Minesweeper")));

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