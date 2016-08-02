package sk.tsystems.hangman;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadLine {

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
}
