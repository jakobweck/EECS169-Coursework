import java.util.Random;
public class Dice {
	private final int numSides;
	//dice constructor
	//assumes valid int passed, sets instance variable representing number of sides
	Dice(int sides){
		numSides = sides;
	}
	//assumes random imported, valid number of sides
	//returns random dice roll
	public int roll(){
		Random rand = new Random();
		int randomInt;
		randomInt = rand.nextInt(numSides) + 1;
		return randomInt;
	}
}
