package com.rithsagea.util;

public class Pair<A, B> {
	
	private A first;
	private B second;
	
	public Pair(A first, B second) {
		this.first = first;
		this.second = second;
	}
	
	public A getFirst() {
		return first;
	}
	
	public B getSecond() {
		return second;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pair) {
			Pair<?,?> p = (Pair<?,?>) obj;
			
			//Deal with null
			return (first == p.first || first.equals(p.first)) &&
					(second == p.second || second.equals(p.second));
		}
		return false;
	}
}
