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
import sk.tsystems.guessthenumber.GuessTheNumber;
import usefullmethods.UserLogin;

/**
 * Servlet implementation class HelloWorldServlet
 */
@WebServlet("/GuessTheNumber")
public class GuessTheNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int guessedNumber = 0;
	int guessCounter;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		HttpSession session = request.getSession();

		GuessTheNumber guessTheNumber = (GuessTheNumber) session.getAttribute("guesstheNumber");
		if (guessTheNumber == null) {
			guessTheNumber = new GuessTheNumber();
			session.setAttribute("guessTheNumber", guessTheNumber);
		}

		out.print("<div align=\"center\">");

		if (guessedNumber == 0 || request.getParameter("newGame") != null) {
			out.print("Welcome traveler. <br>");
			out.print("I guess the number from 1 to 1000. <br>");
			guessedNumber = guessTheNumber.getGuessedNumber();
		}

		int playersGuess = 0;
		String gameName = "GuessTheNumber";

		try {
			playersGuess = Integer.parseInt(request.getParameter("playersGuess"));
		} catch (Exception e) {
		}

		if (playersGuess != guessedNumber) {
			out.println(guessedNumber);
			out.println("Write down your guess.");
			out.println("<form>");
			out.printf("<input type=\"hidden\" name=\"gameName\" value=\"%s\"><br>", gameName);
			out.println("Guess:<input type=\"text\" name=\"playersGuess\"><br>");
			out.println("<input type=\"submit\"><br>");
			out.println("</form>");
		}

		if (playersGuess != guessedNumber) {

			if (playersGuess > guessedNumber && playersGuess != 0) {
				out.println("Bad guess. The number you are trying to guess is lower.<br>");
				guessCounter++;
				out.println("Guess Counter: " + guessCounter);
			}
			if (playersGuess < guessedNumber && playersGuess != 0) {
				out.println("Bad guess. The number you are trying to guess is higher.<br>");
				guessCounter++;
				out.println("Guess Counter: " + guessCounter);
			}

		} else {

			out.println("<form id=\"restart\">");
			out.printf("<a class=\"restartGame\" href=\"?gameName=%s&newGame=%s\" align=\"center\">Restart Game</a>",
					gameName, "NewGame");
			out.println("</form>");
			out.println("<br>");

			out.println("<h2>");
			out.println("<img src=\"image/win2.gif\" alt=\"Win\" align=\"center\">");
			out.println("<br>");
			out.println("Congratulations, you won the Game!");
			out.println("</h2>");
			out.print("</div>");
			out.print("<div align=\"center\">");

			int playersScore = 100 - guessCounter;

			String userName = (String) session.getAttribute("player");

			if (session.getAttribute("player") == null) {
				out.println("You have to log in to comment/rate game.");

				// out.println("<form>");
				// out.printf("<input type=\"hidden\" name=\"gameName\"
				// value=\"%s\"><br>", gameName);
				// out.printf("<input type=\"hidden\"
				// gameName=\"GuessTheNumber\" name=\"playersGuess\"
				// value=\"%s\"><br>",
				// playersGuess);
				// out.println("Write down your name:<input type=\"text\"
				// name=\"playersName\">");
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
				out.printf("<input type=\"hidden\" name=\"playersGuess\" value=\"%s\"><br>", playersGuess);
				out.printf("<input type=\"hidden\" name=\"playersName\" value=\"%s\"><br>", userName);
				out.println("Write down your comment:<input type=\"text\" name=\"playersComment\">");
				out.println("<input type=\"submit\"><br>");
				out.println("</form>");
				request.getParameter("playersRating");
				out.println("<form>");
				out.printf("<input type=\"hidden\" name=\"gameName\" value=\"%s\"><br>", gameName);
				out.printf("<input type=\"hidden\" name=\"playersGuess\" value=\"%s\"><br>", playersGuess);
				out.printf("<input type=\"hidden\" name=\"playersName\" value=\"%s\"><br>", userName);
				out.println("Write down your rating:<input type=\"text\" name=\"playersRating\">");
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

		out.print("<div id=\"leftPart\">");

		request.setAttribute("Comments", new CommentJpqlMethods()
				.printComment(new UsefullServicesJpqlMethods().findGameObjectbyID("GuessTheNumber")));

		out.print("</div>");

		out.print("<div id=\"rightPart\">");

		request.setAttribute("Scores", new ScoreJpqlMethods()
				.printScore(new UsefullServicesJpqlMethods().findGameObjectbyID("GuessTheNumber")));
		out.print("</div>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
