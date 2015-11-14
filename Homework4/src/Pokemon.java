/* -----------------------------------------------------------------------------
 *
 * File Name: Pokemon.java
 * Author: Jakob Wulf-Eck (j104w064@ku.edu)
 * Assignment:   EECS-169 Homework 4
 * Description:  This class represents a Pokemon with variable statistics
 * 				 It contains methods to reset these statistics and to attack other Pokemon.
 * Date: 11/13/2015
 *
 ---------------------------------------------------------------------------- */
public class Pokemon {
	private int hp;
	private int originalHp;
	private int attack;
	private int defense;
	private int attackBonus;
	private int defenseBonus;
	private int damage;
	private boolean dSpellCast;
	private boolean aSpellCast;
	private int roll1;
	private int roll2;
	private int roll3;
	private String name;
	private Dice d20;
	private Dice d6;
	//initializes all class variables to 0/false/empty
	Pokemon(){
		hp = 0;
		originalHp=0;
		attack = 0;
		defense = 0;
		attackBonus = 0;
		defenseBonus = 0;
		damage = 0;
		dSpellCast = false;
		aSpellCast = false;
		name = "";
		d20 = new Dice(20);
		d6 = new Dice(6);
	}
	//handles pokemon attacks/buffs using Dice and prints results
	//requires valid opponent and attack choice passed from main
	//updates statistics based on result of attack
	//returns whether opponent is dead after attack
	//method call in main is assigned to a variable that determines whether the battle loop ends
	public boolean attack(Pokemon opponent, int attackChoice){
		//physical attack
		if (attackChoice == 1){
			System.out.println(this.name +" is attacking " +opponent.getName() + ".");
			attackBonus = d20.roll();
			opponent.setDefenseBonus(d20.roll());
			System.out.println(this.name + " rolls an attack bonus of " + attackBonus + ".");
			System.out.println(opponent.getName() + " rolls a defense bonus of " + opponent.getDefenseBonus() + ".");
			if (this.attackBonus + this.attack > opponent.getDefenseBonus() + opponent.getDefense()){
				System.out.println("The attack hits, dealng 3D6 damage!");
				roll1 = d6.roll();
				roll2 = d6.roll();
				roll3 = d6.roll();
				damage = roll1 + roll2 + roll3 ;
				System.out.println("The rolls are " +roll1 + ", "+ roll2 +", and " + roll3 +
									", totalling: " + this.damage + " damage!");
				opponent.setHp(opponent.getHp() - damage);
				if (opponent.getHp()<=0){
					System.out.println(opponent.getName() + " has been defeated!");
				}
				else{
					System.out.println(opponent.getName() + " has " + opponent.getHp() + " hit points left.");
				}
			}
			else{
				System.out.println("The attack missed!");
			}
		}
		//attack buff - increments user's attack by 5, sets flag so that it can't be used again by the same pokemon
		else if (attackChoice == 2){
			System.out.println(this.name + " casts a spell to improve its attack!");
			attack +=5;
			System.out.println("Its attack permanently increases by 5 points to " + this.attack + "!");
			aSpellCast = true;
		}
		//defense buff
		else if (attackChoice == 3){
			System.out.println(this.name + " casts a spell to improve its defense!");
			defense +=5;
			System.out.println("Its defense permanently increases by 5 points to " + this.defense + "!");
			dSpellCast = true;
		}
		if (opponent.getHp() <= 0){
			return true;
		}
		else{
			return false;
		}
	}
	public void resetStats(){
		//resets pokemon's statistics if it's reused for a second fight
		//remove effects of atk/def buffs if they've been used
		//results in pokemon statistics being identical to those at time of initialization
		if(aSpellCast){
			attack -=5;
		}
		if(dSpellCast){
			defense -=5;
		}
		//buffs can be used again
		aSpellCast = false;
		dSpellCast = false;
		//restore hp to saved original value
		hp = originalHp;
	}
	//getters/setters for all class variables
	//setters assume valid parameters from main, set class variables to parameters
	//getters assume valid class variables, return them
	public void setName(String userName){
		name = userName;
	}
	public void setAttack(int userAttack){
		attack = userAttack;
	}
	public void setDefense(int userDefense){
		defense = userDefense;
	}
	public void setHp(int userHp){
		hp = userHp;
	}
	public void setOriginalHp(int userOrigHp){
		originalHp = userOrigHp;
	}
	public int getAttack(){
		return attack;
	}
	public int getDefense(){
		return defense;
	}
	public int getHp(){
		return hp;
	}
	public String getName(){
		return name;
	}
	public int getDefenseBonus(){
		return defenseBonus;
	}
	public void setDefenseBonus(int userBonus){
		defenseBonus = userBonus;
	}
//	public int getAttackBonus(){
//		return attackBonus;
//	}
//	public void setASpellCast(boolean userASpell){
//		aSpellCast = userASpell;
//	}
//	public void setDSpellCast(boolean userDSpell){
//		dSpellCast = userDSpell;
//	}
	public boolean getASpellCast(){
		return aSpellCast;
	}
	public boolean getDSpellCast(){
		return dSpellCast;
	}
}