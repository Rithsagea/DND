package com.rithsagea.util;

public class MathUtil {
	public static int clamp(int low, int high, int val) {
		if(val < low) return low;
		if(val > high) return high;
		return val;
	}
}
