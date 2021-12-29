package com.rithsagea.util.dice;

import java.util.Random;

public class Dice implements Cloneable {
	
	private int count;
	private int value;
	
	public Dice(int count, int value) {
		this.count = count;
		this.value = value;
	}
	
	public Dice(String code) {
		String[] temp = code.split("d");
		count = Integer.parseInt(temp[0]);
		value = Integer.parseInt(temp[1]);
	}
	
	public int getCount() {
		return count;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int roll(Random rand) {
		int sum = 0;
		for(int x = 0; x < count; x++) {
			sum += rand.nextInt(value) + 1;
		}
		
		return sum;
	}
	
	@Override
	public String toString() {
		return count + "d" + value;
	}
	
	@Override
	public Dice clone() {
		return new Dice(count, value);
	}
}
