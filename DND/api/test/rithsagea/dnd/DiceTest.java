package test.rithsagea.dnd;

import java.util.Random;

import api.rithsagea.dnd.dice.Dice;
import api.rithsagea.dnd.dice.Roll;

public class DiceTest {
	public static void main(String[] args) {
		Roll roll = new Roll();
		Random rand = new Random(29l);
		
		roll.add(new Dice("1d8"));
		roll.add(new Dice(2, 8));
		roll.add(new Dice(3, 10));
		roll.add(6);
		
		roll = new Roll(roll.toString());
		
		System.out.println(roll);
		System.out.println(roll.roll(rand));
	}
}
