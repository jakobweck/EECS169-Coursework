/*
File Name: HangMan.java
Author: Jakob Wulf-Eck 
KUID: 2794175
Email Address: j104w064@ku.edu
Homework Assignment Number: 3 
Description: Hangman game frontend implemented using methods in HangMan class
Last Changed: 11/3/2015
*/
import java.util.Scanner;
public class GameDriver2 {

	public static void main(String[] args) {
		String guess;
		char guessChar;
		String secretWord = "big bang";
		boolean Success = false;
		String playAgainString = "yes";
		boolean playAgain = true;
		Scanner kb = new Scanner(System.in);
		while (playAgain == true){
			System.out.println("Welcome to the Hangman Game!");
			System.out.println("----------------------------");
			HangMan game = new HangMan(secretWord);
			while (game.isGameOver() == false){
				Success = false;
				System.out.println("Guess this: " + game.getDisguisedWord());
				System.out.println("Guesses so far: " + game.getGuessCount());
				System.out.println("Misses: " + game.getMissedMarker());
				System.out.print("Enter your guess character: ");
				guess = kb.nextLine().toLowerCase();
				guessChar = guess.charAt(0);
				Success = game.guessCharacter(guessChar);
				if (Success == true){
					System.out.println(guessChar + " is in the secret word!");
				}
				else if (Success == false){
					System.out.println(guessChar + " is not in the secret word. Death draws closer.");
				}
				System.out.println();
			}
			System.out.println("Game Over!");
			if (game.isFound()){
				System.out.println("Congratulations! You guessed the secret word: " +
										game.getDisguisedWord() +
										" in "+
										game.getGuessCount()+
										" guesses!");
				}
			else if (!game.isFound()){
				System.out.println("You died. Next time, guess as if your life depended on it.");
				}
			System.out.print("Do you want to play again? (yes/no)");
			playAgainString = kb.nextLine();
			if (!playAgainString.toLowerCase().equals("yes")){
					System.out.println();
					System.out.println("Thanks for playing " + game.getGamesPlayed() + " games of Hang Man.");
					playAgain = false;
				}
			else{
				System.out.println("Input a new secret word");
				secretWord = kb.nextLine().toLowerCase();
				for (int i = 0; i<100; i++){
					System.out.println();
					}
				
		    }
		}
	}
}

