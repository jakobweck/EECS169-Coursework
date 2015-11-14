/*File Name: SquareMatrix.java
*Author: Jakob Wulf-Eck 
*KUID: 2794175
*Email Address: j104w064@ku.edu 
*Homework Assignment Number: 2-2 
*Description: Prints the square matrix of a number provided by the user and its transpose 
*Last Changed: 10/4/15 */
import java.util.Scanner;
public class SquareMatrix {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int size = 0;
		System.out.println("Enter the Size of the Square Matrix:");
		size = input.nextInt();
		System.out.println("Square Matrix:");
		// print and increment i until it equals the width/height of the square matrix squared
		for (int i = 1; i<=size*size; i++){
			// if i is a multiple of the width, it must be the end of a row
			// therefore print new line after printing i
			if (i %size == 0){
				System.out.println(i);
			}
			//otherwise just print i
			else{
				System.out.print(i + " ");
			}
		}
		System.out.println("Transpose");
		//row number starts with one
		for (int i = 1; i<=size; i++){
			//row position starts with zero
			for (int j = 0; j<size; j++){
				// print row number plus size times row position
				// this gives row number + 0 for the first position in each row
				// results in flipped matrix
				System.out.print(i + (size*j) + " ");
			}
			System.out.println();
		}
		
	}

}
