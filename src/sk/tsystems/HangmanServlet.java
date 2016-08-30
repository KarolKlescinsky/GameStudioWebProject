package sk.tsystems;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sk.tsystems.hangman.HangManMain;
import sk.tsystems.hangman.ReadLine;

@WebServlet("/hangman")
public class HangmanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		HttpSession session = request.getSession();

		HangManMain hangman = (HangManMain) session.getAttribute("hangman");

		if (hangman == null) {
			hangman = new HangManMain();
			session.setAttribute("hangman", hangman);
		}

		String randomWord = new HangmanServlet().getRandomWord();
		char[] randomWordByCharacters = randomWord.toCharArray();
		char[] playersCorrectGuesses = new char[randomWord.length()];
		char under = '_';
		int playersbadGuesses = 0;

		for (int i = 0; i < randomWord.length(); i++) {
			playersCorrectGuesses[i] = under;
		}

//		out.println(randomWord);
		out.println("I guess the number which is " + randomWord.length() + " long.");

		do {
			System.out.println("Enter character");
			char playersGuess = new ReadLine().readLine().toLowerCase().charAt(0);
			boolean wasPlayersCharCorrect = false;
			System.out.println();

			for (int i = 0; i < randomWord.length(); i++) {
				if (randomWordByCharacters[i] == playersGuess) {
					playersCorrectGuesses[i] = randomWordByCharacters[i];
					wasPlayersCharCorrect = true;
				}
			}
			out.println(playersCorrectGuesses);

			if (wasPlayersCharCorrect) {
				out.println("Good one.");
			} else {
				out.println("Bad one. Try again.");
				out.println(++playersbadGuesses);
			}

			if (Arrays.equals(randomWordByCharacters, playersCorrectGuesses)) {
				out.println("Congratulation, you WON!!!");
				break;
			}

		} while (playersbadGuesses < 8);

		if (playersbadGuesses == 8) {
			out.println("HaHa! You lost. Maybe next time. :)");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public String getRandomWord() throws IOException {
		
		
		
		InputStream is = HangmanServlet.class.getResourceAsStream("Words.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		

	//	BufferedReader reader = new BufferedReader(new FileReader("Words.txt" ));
		String line = reader.readLine();
		List<String> words = new ArrayList<String>();
		while (line != null) {
			String[] wordsLine = line.split(" ");
			for (String word : wordsLine) {
				words.add(word);
			}
			line = reader.readLine();
		}

		Random rand = new Random();
		String randomWord = words.get(rand.nextInt(words.size()));
		reader.close();

		return randomWord;
	}

}
