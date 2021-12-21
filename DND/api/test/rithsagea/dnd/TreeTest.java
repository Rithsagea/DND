package test.rithsagea.dnd;

import java.util.ArrayList;
import java.util.List;

import api.rithsagea.dnd.util.tree.Node;
import api.rithsagea.dnd.util.tree.TreeTool;

public class TreeTest {
	public static class Foo {
		public byte a = 5;
		public short b = 5;
		public int c = 5;
		public long d = 5;
		public float e = 5;
		public double f = 5;
		public boolean g = true;
		public char h = '5';
		public String i = "5";
		public Bar j = new Bar();
	}
	
	public static class Bar {
		public byte a = 6;
		public short b = 6;
		public int c = 6;
		public long d = 6;
		public float e = 6;
		public double f = 6;
		public boolean g = true;
		public char h = '6';
		public String i = "6";
	}
	
	public static class Baz {
		public String a = "7";
		public int b = 7;
		public double c = Math.pow(2, 400);
		
		public String toString() {
			return String.format("(%s, %d, %E)", a, b, c);
		}
	}
	
	public static void main(String[] args) {
		
		Foo foo1 = new Foo();
		Node tree = TreeTool.serialize(foo1);
		Foo foo2 = (Foo) TreeTool.deserialize(tree);
		
		System.out.println(tree);
		System.out.println(TreeTool.serialize(foo2));
		
		int[] intList = new int[] {1, 2, 3, 4, 5};
		Baz[] bazList = new Baz[] {new Baz(), new Baz(), new Baz()};
		tree = TreeTool.serialize(bazList);
		System.out.println(TreeTool.serialize(intList));
		System.out.println(tree);
		System.out.println(TreeTool.deserialize(tree));
		
		List<Object> mixedList = new ArrayList<>();
		mixedList.add(new Baz());
		mixedList.add(5);
		mixedList.add(false);
		mixedList.add("asdf\n\"asdf\"");
		tree = TreeTool.serialize(mixedList);
		System.out.println(tree);
		System.out.println(mixedList);
		System.out.println(TreeTool.deserialize(tree));
		
		String str = TreeTool.toString(foo1);
		System.out.println(str);
		
		System.out.println(Double.toString(Math.pow(2, 400)));
		System.out.println(Double.parseDouble(Double.toString(Math.pow(2, 400))));
	}
}
