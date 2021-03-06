package sk.tsystems;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.servicesjpql.CommentJpqlMethods;
import services.servicesjpql.RatingJpqlMethods;
import services.servicesjpql.ScoreJpqlMethods;
import services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("AvgMinesweeper", new RatingJpqlMethods().averageRating("Minesweeper"));
		request.setAttribute("AvgStones", new RatingJpqlMethods().averageRating("Stones"));
		request.setAttribute("AvgGuessTheNumber", new RatingJpqlMethods().averageRating("GuessTheNumber"));

		request.setAttribute("CountMinesweeper", new RatingJpqlMethods().countOfRatings("Minesweeper"));
		request.setAttribute("CountStones", new RatingJpqlMethods().countOfRatings("Stones"));
		request.setAttribute("CountGuessTheNumber", new RatingJpqlMethods().countOfRatings("GuessTheNumber"));

		request.setAttribute("CountCommentMinesweeper", new CommentJpqlMethods().countOfComments("Minesweeper"));
		request.setAttribute("CountCommentStones", new CommentJpqlMethods().countOfComments("Stones"));
		request.setAttribute("CountCommentGuessTheNumber", new CommentJpqlMethods().countOfComments("GuessTheNumber"));

		request.setAttribute("CountPlayerMinesweeper", new UsefullServicesJpqlMethods().countOfPlayers("Minesweeper"));
		request.setAttribute("CountPlayerStones", new UsefullServicesJpqlMethods().countOfPlayers("Stones"));
		request.setAttribute("CountPlayerGuessTheNumber",
				new UsefullServicesJpqlMethods().countOfPlayers("GuessTheNumber"));

		request.setAttribute("MaxMinesweeper", new ScoreJpqlMethods().highScore("Minesweeper"));
		request.setAttribute("MaxStones", new ScoreJpqlMethods().highScore("Stones"));
		request.setAttribute("MaxGuessTheNumber", new ScoreJpqlMethods().highScore("GuessTheNumber"));

		request.setAttribute("countofplayers", new UsefullServicesJpqlMethods().numberOfPlayers());
		request.setAttribute("countofgames", new UsefullServicesJpqlMethods().numberOfGames());

		if (request.getParameter("gameName") != null) {
			switch (request.getParameter("gameName")) {
			case "Stones":
				request.setAttribute("gameName", request.getParameter("gameName"));
				RequestDispatcher stones = request.getRequestDispatcher("/WEB-INF/jsp/DefaultGS.jsp");
				request.setAttribute("hideRegistration", "hidden");
				stones.forward(request, response);
				break;
			case "Minesweeper":
				request.setAttribute("gameName", request.getParameter("gameName"));
				RequestDispatcher minesweeper = request.getRequestDispatcher("/WEB-INF/jsp/DefaultGS.jsp");
				request.setAttribute("hideRegistration", "hidden");
				minesweeper.forward(request, response);
				break;
			case "GuessTheNumber":
				request.setAttribute("gameName", request.getParameter("gameName"));
				RequestDispatcher guessthenumber = request.getRequestDispatcher("/WEB-INF/jsp/DefaultGS.jsp");
				request.setAttribute("hideRegistration", "hidden");
				guessthenumber.forward(request, response);
				break;
			// case "hangman":
			// request.setAttribute("gameName",
			// request.getParameter("gameName"));
			// RequestDispatcher hangman =
			// request.getRequestDispatcher("/WEB-INF/jsp/DefaultGS.jsp");
			// hangman.forward(request, response);
			// break;
			case "Login":
				request.setAttribute("gameName", request.getParameter("gameName"));
				RequestDispatcher login = request.getRequestDispatcher("/WEB-INF/jsp/DefaultGS.jsp");
				request.setAttribute("hideTables", "hidden");
				request.setAttribute("hideLoginTable", "hidden");
				login.include(request, response);
				break;
			}
		} else {
			request.setAttribute("hideRegistration", "hidden");
			request.setAttribute("hideTables", "hidden");
			request.getRequestDispatcher("/WEB-INF/jsp/DefaultGS.jsp").forward(request, response);
		}
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
