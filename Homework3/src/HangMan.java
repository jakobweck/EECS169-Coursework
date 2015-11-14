/*
File Name: HangMan.java
Author: Jakob Wulf-Eck 
KUID: 2794175
Email Address: j104w064@ku.edu
Homework Assignment Number: 3 
Description: Class containing backend methods for a hangman game implemented by GameDriver.java
Last Changed: 11/3/2015
*/
public class HangMan {
	private final String secret_word;
	private char[] disguised_word;
	private int guessCount;
	private int missesCount;
	private final int MAX_MISSES_ALLOWED;
	private char[] missedMarkers;
	private int wordGuessesMade;
	private static int gamesPlayed = 0;
	public HangMan(String secret) {
		   secret_word = secret;
		   guessCount = 0;
		   missesCount = 0;
		   wordGuessesMade = 0;
		   MAX_MISSES_ALLOWED = 7;
		   missedMarkers = new char[MAX_MISSES_ALLOWED];
		   disguised_word  = new char[secret_word.length()];
		   //disguised_word is the secret phrase but all question marks
		   for (int i = 0; i< disguised_word.length; i++){
			   if (secret_word.charAt(i) == ' '){
				   disguised_word[i] = ' ';
			   }
			   else{
				   disguised_word[i] = '?';
			   }
		   }
		   for (int i = 0; i<missedMarkers.length; i++){
			   missedMarkers[i] = 'O';
		   }
		   gamesPlayed++;
	
	}
	public boolean guessCharacter(char c){
		boolean charFound = false;
		guessCount++;
		//search the secret word for the char
		//if found, 'reveal' it in disguised_word by changing the question mark
		for (int i = 0; i < secret_word.length(); i++){
		    if (secret_word.charAt(i) == c){
		    	disguised_word[i] = c;
		    	charFound = true;
		    }
		}
		if (charFound == false){
			missesCount++;
			return false;
			}
		else{
			return true;
		}
	}
	public boolean guessWord(String word){
		guessCount++;
		wordGuessesMade++;
		//if the guess matches the secret word exactly, completely replace disguised_word with the secret
		if (word.equals(secret_word)){
			for (int i = 0; i<secret_word.length(); i++){
				disguised_word[i] = secret_word.charAt(i);}
			return true;
		}	
		else{
			return false;
		}
	}
	public boolean areWordGuessesAllowed(){
		if (wordGuessesMade>0){
			return false;
		}
		else{
			return true;
		}
	}
	public boolean isGameOver(){
		//end game if word is found or user misses too many times
		if (tooManyMisses() || isFound()){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isFound(){
		//word is found if all ?s in disguised_word are replaced
		if (getDisguisedWord().equals(secret_word)){
			return true;
		}
		else{
			return false;
		}
	}
	//turn disguised word into a string for printing
	public String getDisguisedWord(){
		String disguisedString = new String(disguised_word);
		return disguisedString;
	}
	public int getGuessCount(){
		return guessCount;
	}
	public int getMissesCount(){
		return missesCount;
	}
	//replace an O in missedMarker with an x for every miss
	//return it as a string
	public String getMissedMarker(){
		for (int i=0; i<MAX_MISSES_ALLOWED; i++){
			if (i<missesCount){
				missedMarkers[i] = 'X';
			}
			else{
				missedMarkers[i] = 'O';
			}
		}
		String missedString = new String(missedMarkers);
		return missedString;
	}
	public int getGamesPlayed(){
		return gamesPlayed;
	}
	//check if max misses are exceeded
	//doesn't pass to anything in GameDriver, so is private
	private boolean tooManyMisses(){
		if (missesCount >= MAX_MISSES_ALLOWED){
			return true;
		}
		else{
			return false;
		}
	}
}


