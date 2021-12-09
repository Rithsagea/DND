package api.rithsagea.dnd.util;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Dice {

	private Set<Die> dice;
	
	public Dice() {
		dice = new TreeSet<>();
	}
	
	public void addDie(Die die) {
		for(Die d : dice) {
			if(d.value == die.value) {
				d.quantity += die.quantity;
				return;
			}
		}
		
		dice.add(die);
	}
	
	public int maxValue() {
		int sum = 0;
		for(Die d : dice) {
			sum += d.value * d.quantity;
		}
		
		return sum;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		String prefix = "";
		
		for(Die die : dice) {
			builder.append(prefix);
			builder.append(die.toString());
			prefix = " + ";
		}
		
		return builder.toString();
	}
	
	public static class Die implements Comparable<Die> {
		private int value;
		private int quantity;

		public Die(int value, int quantity) {
			this.value = value;
			this.quantity = quantity;
		}

		/**
		 * @return the value
		 */
		public int getValue() {
			return value;
		}

		/**
		 * @return the quantity
		 */
		public int getQuantity() {
			return quantity;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(int value) {
			this.value = value;
		}

		/**
		 * @param quantity the quantity to set
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		@Override
		public int compareTo(Die d) {
			return value - d.value;
		}

		public int roll(Random rand) {
			int res = 0;
			List<Integer> rolls = rand.ints(quantity).boxed().collect(Collectors.toList());
			for(int val : rolls) res += val;
			return res;
		}

		public int getMax() {
			return value * quantity;
		}
		
		public String toString() {
			return String.format("%dd%d", quantity, value);
		}
	}
}
