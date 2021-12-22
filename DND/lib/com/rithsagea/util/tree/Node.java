package com.rithsagea.util.tree;

import java.util.Collection;
import java.util.Map;

import com.rithsagea.util.tree.Leaf.BooleanLeaf;
import com.rithsagea.util.tree.Leaf.ByteLeaf;
import com.rithsagea.util.tree.Leaf.CharacterLeaf;
import com.rithsagea.util.tree.Leaf.DoubleLeaf;
import com.rithsagea.util.tree.Leaf.FloatLeaf;
import com.rithsagea.util.tree.Leaf.IntegerLeaf;
import com.rithsagea.util.tree.Leaf.LongLeaf;
import com.rithsagea.util.tree.Leaf.ObjectLeaf;
import com.rithsagea.util.tree.Leaf.ShortLeaf;
import com.rithsagea.util.tree.Leaf.StringLeaf;

public class Node {
	
	public byte asByte() {
		return ((ByteLeaf) this).get();
	}
	
	public short asShort() {
		return ((ShortLeaf) this).get();
	}
	
	public int asInteger() {
		return ((IntegerLeaf) this).get();
	}
	
	public long asLong() {
		return ((LongLeaf) this).get();
	}
	
	public float asFloat() {
		return ((FloatLeaf) this).get();
	}
	
	public double asDouble() {
		return ((DoubleLeaf) this).get();
	}

	public char asCharacter() {
		return ((CharacterLeaf) this).get();
	}
	
	public String asString() {
		return ((StringLeaf) this).get();
	}
	
	public boolean asBool() {
		return ((BooleanLeaf) this).get();
	}
	
	public Object asObject() {
		return ((ObjectLeaf) this).get();
	}
	
	public Array asList() {
		return (Array) this;
	}
	
	public Tree asTree() {
		return (Tree) this;
	}

	//Static Convenience
	
	public static Node auto(Object obj) {
		if(obj instanceof Node) return (Node) obj; // do nothing
		if(obj instanceof Byte) return Leaf.of((byte) obj);
		if(obj instanceof Short) return Leaf.of((short) obj);
		if(obj instanceof Integer) return Leaf.of((int) obj);
		if(obj instanceof Long) return Leaf.of((long) obj);
		if(obj instanceof Float) return Leaf.of((float) obj);
		if(obj instanceof Double) return Leaf.of((double) obj);
		if(obj instanceof Character) return Leaf.of((char) obj);
		if(obj instanceof String) return Leaf.of((String) obj);
		if(obj instanceof Boolean) return Leaf.of((boolean) obj);
		
		if(obj instanceof Object[]) return Array.of((Object[]) obj);
		if(obj instanceof byte[]) return Array.of((byte[]) obj);
		if(obj instanceof short[]) return Array.of((short[]) obj);
		if(obj instanceof int[]) return Array.of((int[]) obj);
		if(obj instanceof long[]) return Array.of((long[]) obj);
		if(obj instanceof float[]) return Array.of((float[]) obj);
		if(obj instanceof double[]) return Array.of((double[]) obj);
		if(obj instanceof char[]) return Array.of((char[]) obj);
		if(obj instanceof boolean[]) return Array.of((boolean[]) obj);
		
		if(obj instanceof Collection<?>) return Array.of((Collection<?>) obj);
		
		//Will break if map is of invalid key
		if(obj instanceof Map) return Tree.of((Map<?, ?>) obj);
		
		return Leaf.of(obj);
	}
}
