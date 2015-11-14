/*File Name: AsciiArt.java
*Author: Jakob Wulf-Eck 
*KUID: 2794175
*Email Address: j104w064@ku.edu 
*Homework Assignment Number: 2-1 
*Description: Prints various ASCII grids of layout and size determined by user 
*Last Changed: 10/4/15 */
import java.util.Scanner;
public class AsciiArt {

	public static void main(String[] args) {
		Scanner input = new Scanner (System.in);
		int pattern_number;
		int size;
		System.out.println("Choose one of the following patterns by typing the corresponding number:\n"
							+ "1) Stripes\n"
							+ "2) Checker Board\n"
							+ "3) Double Diagonal (aka the X)\n"
							+ "4) Two Islands");
		pattern_number = input.nextInt();
		//stripes
		if (pattern_number == 1){
			System.out.println("Input a size(must be larger than 1)");
			size = input.nextInt();
			if (size < 1){
				System.out.println("Error.");
			}
			else{
			//outer loop increments row
			for (int i = 0; i<size; i++){
				// inner loop increments column
				for (int j = 0; j<=size; j++){
					if (j == 0){
						//print line number
						System.out.print(i+" ");}
					// spaces at odd positions, asterisks at even ones
					else if (j%2 == 0){
						System.out.print("  ");}
					else if (j%2 != 0){
						System.out.print("*");}
				}
				System.out.println("");
			}}
		}
		//checkerboard
		else if (pattern_number == 2){
			System.out.println("Input a size(must be larger than 1)");
			size = input.nextInt();
			if (size < 1){
				System.out.println("Error.");
			}
			else{
			for (int i = 0; i<size; i++){
				// do this on even rows and 0th row
				// asterisks at odd positions
				if(i%2 == 0){
					for (int j = 0; j<=size; j++){
						if (j==0){
							System.out.print(i + " ");
						}
						else if (j%2 == 0){
							System.out.print(" ");
						}
						else if (j%2 !=0){
							System.out.print("*");
						}
						}
				}
				// do this on odd rows
				// asterisks at even positions
				else if(i%2 != 0){
					for (int j = 0; j<=size; j++){
						if (j==0){
							System.out.print(i + " ");
						}
						else if (j%2 == 0){
							System.out.print("*");
						}
						else if (j%2 !=0){
							System.out.print(" ");
						}
					}
				}
				System.out.println("");
			}
			}
		}
		//double diagonals
		else if (pattern_number == 3){
			System.out.println("Input a size(must be larger than 1)");
			size = input.nextInt();
			if (size < 1){
				System.out.println("Error.");
			}
			else{
			for (int i =0; i<size; i++){
				for (int j = 0; j<=size; j++){
					if (j == 0){
						System.out.print(i + " ");
					}
					// asterisks appear at (line number(i) + 1) and (line length(size) - line number) positions in the line
					// i.e. if size=5, on row 0 at 1st and 5th positions, row 1 at 2nd and 4th
					else if (j == i+1 || j == size-i){
						System.out.print("*");
					}
					// all other positions are spaces
					else{
						System.out.print(" ");
					}
					//
				}
				System.out.println("");
			}
			}
		}
		//two islands
		else if (pattern_number == 4){
			System.out.println("Input a size(must be larger than 1)");
			size = input.nextInt();
			if (size < 1){
				System.out.println("Error.");
			}
			else{
			for (int i = 0; i<size; i++){
				for( int j =0; j<=size; j++){
					if (j==0){
						System.out.print(i + " ");
					}
					// if in top left quadrant of output, print ! (first island)
					else if (i< (size/2) && j <= (size/2)){
						System.out.print("!");
					}
					// if in bottom right quadrant of output, print ? (second island)
					else if (i >= (size/2 + size%2) && j> (size/2 + size%2)){
						System.out.print("?");
					}
					// draws diagonal line of asterisks
					else if (j == size-i){
						System.out.print("*");
					}
					// all other positions are filled with tildes
					else{
						System.out.print("~");
					}
				}
				System.out.println("");
			}
		}
		}
		else{
			System.out.println("Error.");
		}
		
			
	}

}
