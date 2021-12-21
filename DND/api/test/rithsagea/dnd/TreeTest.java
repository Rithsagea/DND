package test.rithsagea.dnd;

import api.rithsagea.dnd.util.tree.Array;
import api.rithsagea.dnd.util.tree.Tree;

public class TreeTest {
	public static void main(String[] args) {
		Tree tree = new Tree();
		
		tree.set("String", "string");
		tree.set("Integer", 5);
		tree.set("Double", 5d);
		tree.set("Long", 5l);
		tree.set("Array", Array.of("String", 5, 5d, 5l));
		
		System.out.println(tree);
	}
}
