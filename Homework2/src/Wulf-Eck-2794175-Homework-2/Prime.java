/*File Name: Prime.java
*Author: Jakob Wulf-Eck 
*KUID: 2794175
*Email Address: j104w064@ku.edu 
*Homework Assignment Number: 2-3 [169 Only]
*Description: Determines whether a number specified by the user is prime or not.
*Last Changed: 10/4/15 */
import java.util.Scanner;
public class Prime {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num_tested = 0;
		// assume user's number is prime until proven otherwise
		String answer = " is prime.";
		System.out.print("Input a positive integer: ");
		num_tested = input.nextInt();
		if (num_tested <= 0){
			System.out.println("Error: Input a positive integer");
		}
		else if (num_tested == 1){
			System.out.println("Not prime, but worth talking to a Professor of Maths about.");
		}
		else{
		for (int i = 2; i<num_tested; i++){
			// divide the user's number by every integer greater than 1 leading up to it
			// if it is evenly divisible by any of them, it's not prime
			if ((num_tested%i) == 0){
				answer = " is not prime.";
			}
		}
		System.out.println (num_tested + answer);
	}
	}

}
