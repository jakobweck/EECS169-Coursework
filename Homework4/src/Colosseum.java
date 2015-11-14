/* -----------------------------------------------------------------------------
 *
 * File Name: Colosseum.java
 * Author: Jakob Wulf-Eck (j104w064@ku.edu)
 * Assignment:   EECS-169 Homework 4
 * Description:  This program implements the Pokemon and Dice classes to simulate a Pokemon battle.
 * Date: 11/13/2015
 *
 ---------------------------------------------------------------------------- */
import java.util.Scanner;
public class Colosseum {
	static int playerNumber;
	public static void main(String[] args){
		Scanner mainInput = new Scanner(System.in);
		int attackChoice;
		int roll = 0;
		int loser = 0;
		int round = 1;
		int roundStarter;
		String playAgainString = "";
		boolean playAgain = true;
		//by default, make two new pokemon when the main game loop begins
		int reuseChoice = 3;
		Pokemon p1 = new Pokemon();
		Pokemon p2 = new Pokemon();
		while (playAgain == true){
			playerNumber = 1;
			boolean gameOver = false;
			Dice coin = new Dice(2);
			//user can change reuseChoice at end of main game loop
			//build/rebuild both pokemon
			if (reuseChoice == 3){
				userBuild(p1);
				playerNumber++;
				userBuild(p2);
			}
			//rebuild losing pokemon, reset stats of both
			else if (reuseChoice == 2){
				if (loser == 1){
					p1.resetStats();
					p2.resetStats();
					playerNumber = 1;
					userBuild(p1);
				}
				else if (loser == 2){
					p1.resetStats();
					p2.resetStats();
					playerNumber = 2;
					userBuild(p2);
				}
			}
			//don't build any new pokemon, but reset stats
			else if (reuseChoice == 1){
				p1.resetStats();
				p2.resetStats();
			}
			System.out.println("Player 1 will roll a D2 to decide who goes first.");
			roll = coin.roll();
			//current player is set to the winner of the d2 roll for now
			//that player is permanently recorded as roundStarter 
			if (roll == 1){
				System.out.println("Player 1 rolls a 1 and will go first.");
				playerNumber = 1;
				roundStarter = 1;
			}
			else{
				System.out.println("Player 1 rolls a 2 and will go second.");
				playerNumber = 2;
				roundStarter = 2;
			}
			//battle loop
			while (gameOver == false){
				//only print round number when the first player to attack comes around again
				if (playerNumber == roundStarter){
					System.out.println("ROUND " + round +"!");
					System.out.println();
					round++;
				}
				System.out.print("Player " +playerNumber+ ", attack, use an attack spell, or use a defense spell? (1,2,3): ");
				attackChoice = mainInput.nextInt();
				System.out.println();
				if (playerNumber == 1){
					//while loop prevents invalid int input
					while (attackChoice != 1 && attackChoice !=2 && attackChoice !=3){
						System.out.println("Please choose a valid move(1, 2, or 3)");
						attackChoice = mainInput.nextInt();
					}
					//these while loops prevent reuse of atk/def buffs
					while (attackChoice == 2 && p1.getASpellCast() == true){
						System.out.println("You already cast that spell, pick again!");
						attackChoice = mainInput.nextInt();
					}
					while (attackChoice == 3 && p1.getDSpellCast() == true){
						System.out.println("You already cast that spell, pick again!");
						attackChoice = mainInput.nextInt();
				}
					//game over is set to result of attack function
					//if the target dies, it returns True and ends the battle
					gameOver = p1.attack(p2, attackChoice);
					playerNumber = 2;
				}
				else if (playerNumber == 2){
					while (attackChoice != 1 && attackChoice !=2 && attackChoice !=3){
						System.out.println("Please choose a valid move(1, 2, or 3)");
						attackChoice = mainInput.nextInt();
					}
					while (attackChoice == 2 && p2.getASpellCast() == true){
						System.out.println("You already cast that spell, pick again!");
						attackChoice = mainInput.nextInt();
					}
					while (attackChoice == 3 && p2.getDSpellCast() == true){
						System.out.println("You already cast that spell, pick again!");
						attackChoice = mainInput.nextInt();
				}
					gameOver = p2.attack(p1, attackChoice);
					playerNumber = 1;
				}
				//fights only go 10 rounds
				//at what would be start of the 11th round, force the fight to end
				if (round == 11 && playerNumber == roundStarter){
					System.out.println("Both fighters are still standing! It's a draw!");
					gameOver = true;
				}
			}
			//loser is whoever was next up to attack when game ended because only the current attacker can win
			loser = playerNumber;
			while(!playAgainString.toLowerCase().equals("yes") && !playAgainString.toLowerCase().equals("no")){
				mainInput.nextLine();
				System.out.println();
				System.out.print("Game over! Play again? (yes/no):");
				playAgainString = mainInput.nextLine();
			}
			//if user wants to play again, let them change reuseChoice
			//can reuse both, only losing, or neither pokemon
			if (playAgainString.toLowerCase().equals("yes")){
					System.out.println("Reuse Pokemon?\n1:Both\n2:Winner Only\n3:Neither");
					reuseChoice = mainInput.nextInt();
					while ((reuseChoice != 1 && reuseChoice != 2 && reuseChoice != 3)){
						reuseChoice = mainInput.nextInt();
					}
					round = 1;
					playAgain = true;
					playAgainString = "";
					}
			else{
				playAgain = false;
			}
			
		}
	}
	//takes user input to build pokemon
	//assumes valid, initialized pokemon instance
	//results in pokemon instance variables set to user specifications
	public static void userBuild(Pokemon p){
		String userName = "";
		int userHP = 0;
		int userAttack = 0;
		int userDefense = 0;
		int maxDefense = 0;
		Scanner buildInput = new Scanner(System.in);
		System.out.println("Player " + playerNumber +", build your Pokemon!");
		System.out.println("=====================");
		System.out.print("Please name your Pokemon: ");
		userName = buildInput.nextLine();
		System.out.println();
		//while loops prevent invalid input (at least if it's of the proper type)
		while(userName == ""){
			System.out.print("The Pokemon's name cannot be blank.");
			userName = buildInput.nextLine();
			System.out.println();
		}
		p.setName(userName);
		System.out.print("How many hit points will it have? (1-50): ");
		userHP = buildInput.nextInt();
		System.out.println();
		while (!(userHP <= 50) || !(userHP>=1)){
			System.out.print("Sorry. The hit points must be between 1 and 50: ");
			userHP = buildInput.nextInt();
			System.out.println();
		}
		p.setHp(userHP);
		//'originalHp' is set equal to the user's entered HP value and never changed
		//can be used to restore the pokemon to full health if it's used for a second fight
		p.setOriginalHp(userHP);
		System.out.println("Split fifty points between attack level and defense level.");
		System.out.print("Enter your attack level (1-49): ");
		userAttack = buildInput.nextInt();
		System.out.println();
		while (!(userAttack < 50) || !(userAttack>=1)){
			System.out.println("Sorry. The attack level must be between 1 and 50: ");
			userAttack = buildInput.nextInt();
			System.out.println();
		}
		p.setAttack(userAttack);
		//defense and attack can't add up to more than 50 together
		//but defense + atack doesn't HAVE to equal 50
		maxDefense = 50-userAttack;
		System.out.print("Enter your defense level (1-" +maxDefense + "): ");
		userDefense = buildInput.nextInt();
		System.out.println();
		while (!(userDefense <= maxDefense) || !(userDefense>=1)){
			System.out.println("Sorry. The defense level must be between 1 and " + maxDefense +":");
			userDefense = buildInput.nextInt();
			System.out.println();
		}
		p.setDefense(userDefense);
		System.out.println();
	}
}
