package com.rithsagea.util.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roll {
	
	private int constant;
	private List<Dice> dice;
	
	public Roll() {
		constant = 0;
		dice = new ArrayList<>();
	}
	
	public Roll(String code) {
		this();
		
		String[] temp = code.split("\\+");
		for(String item : temp) {
			if(item.contains("d")) {
				add(new Dice(item));
			} else {
				constant += Integer.parseInt(item);
			}
		}
	}
	
	public void add(Dice die) {
		for(Dice d : dice) {
			if(d.getValue() == die.getValue()) {
				d.setCount(d.getCount() + die.getCount());
				return;
			}
		}
		
		dice.add(die);
	}
	
	public void add(int value) {
		constant += value;
	}
	
	public int roll(Random rand) {
		int sum = constant;
		for(Dice die : dice) {
			sum += die.roll(rand);
		}
		
		return sum;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		for(Dice die : dice) {
			builder.append(prefix);
			builder.append(die);
			
			prefix = "+";
		}
		
		if(constant > 0) builder.append(prefix + constant);
		
		return builder.toString();
	}
}
