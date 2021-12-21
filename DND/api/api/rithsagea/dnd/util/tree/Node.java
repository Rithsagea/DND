package api.rithsagea.dnd.util.tree;

import api.rithsagea.dnd.util.tree.Leaf.BooleanLeaf;
import api.rithsagea.dnd.util.tree.Leaf.DoubleLeaf;
import api.rithsagea.dnd.util.tree.Leaf.IntegerLeaf;
import api.rithsagea.dnd.util.tree.Leaf.LongLeaf;
import api.rithsagea.dnd.util.tree.Leaf.ObjectLeaf;
import api.rithsagea.dnd.util.tree.Leaf.StringLeaf;

public class Node {
	
	public Object asObj() {
		return ((ObjectLeaf) this).get();
	}
	
	public String asString() {
		return ((StringLeaf) this).get();
	}
	
	public int asInt() {
		return ((IntegerLeaf) this).get();
	}
	
	public long asLong() {
		return ((LongLeaf) this).get();
	}
	
	public double asDouble() {
		return ((DoubleLeaf) this).get();
	}

	public boolean asBool() {
		return ((BooleanLeaf) this).get();
	}
	
	public Array asList() {
		return (Array) this;
	}
	
	public Tree asTree() {
		return (Tree) this;
	}
}
