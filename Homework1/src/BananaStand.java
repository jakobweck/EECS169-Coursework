/*File Name: BananaStand.java
 * Author: Jakob Wulf-Eck
 * KUID: 2794175
 * Email Address: j104w064@ku.edu
 * Homework Assignment Number: 1-2
 * Description: Simulates ordering various quantities of bananas, calculates prices. Supports user input of shipping info.
 * Last Changed: 9/21/2015
 */
import java.util.Scanner;

public class BananaStand {

	public static void main(String[] args) {
		//init. scanner and input variables
		Scanner input = new Scanner(System.in);
		int num_bananas = 0;
		double base_cost = 0.0;
		char banana_type;
		char banana_color;
		final double COST_PER_BANANA = .03;
		final double ORGANIC_MARKUP_PERCENTAGE = .20;
		final double GAMMA_RAY_MARKUP_PERCENTAGE  = .50;
		final double GREEN_DISCOUNT_PERCENTAGE = .10;
		final double MIXED_DISCOUNT_PERCENTAGE = .05;
		
		
		//get banana type input
		System.out.print("Welcome to the Banana Stand! \n"
				+ "-------------------- \n"
				+ "Select type of banana:\n"
				+ "Regular (r/R) (no markup)\n"
				+ "Organic (o/O) (20% markup)\n"
				+ "Saturated with Gamma Rays (g/G) (50% markup)\n"
				+ "Input your choice: ");
		// banana_type is set to first char that user inputs
		banana_type = input.nextLine().charAt(0);
		
		
		// get banana color input
		System.out.print("-------------------- \n"
				+ "Select color of banana:\n"
				+ "Yellow (y/Y) (no discount)\n"
				+ "Green (g/G) (10% discount)\n"
				+ "Mixed (m/M) (5% discount)\n"
				+ "Input your choice: ");
		banana_color = input.nextLine().charAt(0);
		
		
		// get number of bananas input
		System.out.print("--------------------\n"
				+ "How many bananas do you want?: ");
		num_bananas = input.nextInt();
		
		
		// initialize variables for shipping address
		String state = "";
		String city = "";
		String streetAddress = "";
		int zipCode = 00000;
		final double OUT_OF_STATE_SHIPPING = 35.50;
		
		
		// get shipping info from user
		System.out.print("--------------------\n"
				+ "Input your street address: ");
		input.nextLine();
		streetAddress = input.nextLine();
		System.out.print("Input your city: ");
		city = input.nextLine();
		System.out.print("Input your state: ");
		state = input.nextLine();
		System.out.print("Input your zip code: ");
		zipCode = input.nextInt();
		System.out.println("--------------------");
		
		
     	// calculate cost before markup/discount
		base_cost = num_bananas * COST_PER_BANANA;
		System.out.print("Total cost for " + num_bananas);
		System.out.printf(" banana(s) before markups or discounts: $%.2f\n", base_cost);
		
		
		// calculate/print markup based on user's choice
		double total_markup = 0.00;
		if (banana_type == 'o' || banana_type == 'O'){
			total_markup = base_cost * ORGANIC_MARKUP_PERCENTAGE;
		}else if (banana_type == 'g' || banana_type == 'G'){
			total_markup = base_cost * GAMMA_RAY_MARKUP_PERCENTAGE;
		}else{
			total_markup = 0.00;}
		System.out.printf("Total markups: $%.2f\n", total_markup);
		
		
		// calculate/print discount based on user's choice
		double total_discount = 0.00;
		if (banana_color == 'g' || banana_color == 'G'){
			total_discount = base_cost * GREEN_DISCOUNT_PERCENTAGE;
		}else if (banana_color == 'm' || banana_color == 'M'){
			total_discount = base_cost * MIXED_DISCOUNT_PERCENTAGE;
		}else{
			total_discount = 0.00;}
		System.out.printf("Total discounts: $%.2f\n", total_discount);
		
		
		// calculate/print total cost after markups/discount/shipping
		double total_cost;
		if (state.toLowerCase().equals("kansas")){
			total_cost = base_cost + total_markup - total_discount;
		}else{
			total_cost = base_cost + total_markup - total_discount + OUT_OF_STATE_SHIPPING;
		}
		System.out.printf("Total cost: $%.2f", total_cost);
		
		
		// print address
		System.out.println("\nShipping to:\n"
				+ "\t" + streetAddress + "\n"
				+ "\t" + city + "\n"
				+ "\t" + state + "\n"
				+ "\t" + zipCode);
		
		
		// init. variables for calculating shipping methods
		final int BANANAS_PER_BUNCH = 8;
		final int BANANAS_PER_BARREL = 64;
		final int BANANAS_PER_TRUCK = 2400;
		int trucks = 0;
		int barrels = 0;
		int bunches = 0;
		int singles = 0;
		
		
		// initialize copy of num_bananas to subtract without affecting original value
		int banana_counter = num_bananas;
		// if more than 2400 bananas, subtract by 2400s and increment number of trucks
		if (banana_counter >= BANANAS_PER_TRUCK){
			while (banana_counter >= BANANAS_PER_TRUCK){
				banana_counter -= BANANAS_PER_TRUCK;
				trucks +=1;
			}
		}
		
		// if still more than 64 bananas, subtract by 64s and increment number of barrels
		if (banana_counter < BANANAS_PER_TRUCK && banana_counter >= BANANAS_PER_BARREL){
			while (banana_counter >= BANANAS_PER_BARREL){
				banana_counter -= BANANAS_PER_BARREL;
				barrels +=1;
			}
		}
		
		// if still more than 8 bananas, subtract by 8s and increment number of bunches
		if (banana_counter < BANANAS_PER_BARREL && banana_counter >= BANANAS_PER_BUNCH){
			while (banana_counter >= BANANAS_PER_BUNCH){
				banana_counter -= BANANAS_PER_BUNCH;
				bunches +=1;
			}
		}
		
		// remaining bananas are singles
		singles = banana_counter;
		// print shipping methods
		System.out.println("Your order of " + num_bananas + 
				" will be shipped to you in " 
				+ trucks + " truck(s), " 
				+ barrels + " barrel(s), " 
				+ bunches + " bunch(es), and " 
				+ singles + " singles.");
		

			
		}
			
		
				

	}