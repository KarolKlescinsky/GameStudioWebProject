package sk.tsystems.hangman;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HangManMain {

	public static void main(String[] args) throws IOException {

		HangManMain hangman = new HangManMain();

		String randomWord = hangman.getRandomWord();
		char[] randomWordByCharacters = randomWord.toCharArray();
		char[] playersCorrectGuesses = new char[randomWord.length()];
		char under = '_';
		int playersbadGuesses = 0;

		for (int i = 0; i < randomWord.length(); i++) {
			playersCorrectGuesses[i] = under;
		}

		System.out.println(randomWord);
		System.out.println("I guess the number which is " + randomWord.length() + " long.");

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
			System.out.println(playersCorrectGuesses);

			if(wasPlayersCharCorrect){
				System.out.println("Good one.");
			} else {
				System.out.println("Bad one. Try again.");
				System.out.println(++playersbadGuesses);
			}
			
			if(Arrays.equals(randomWordByCharacters, playersCorrectGuesses)){
				System.out.println("Congratulation, you WON!!!");
				break;
			}

		} while (playersbadGuesses<8);
		
			if(playersbadGuesses==8){
				System.out.println("HaHa! You lost. Maybe next time. :)");
			}

	}

	public String getRandomWord() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("Words.txt"));
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
