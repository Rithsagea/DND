package api.rithsagea.dnd.util.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Array extends Node implements Iterable<Node> {
	private List<Node> values;
	
	public Array() {
		values = new ArrayList<>();
	}
	
	public Node get(int pos) {
		return values.get(pos);
	}
	
	public void set(int pos, Node node) {
		values.set(pos, node);
	}
	
	public void set(int pos, Object obj) {
		set(pos, Leaf.auto(obj));
	}
	
	public void add(Node node) {
		values.add(node);
	}
	
	public void add(Object obj) {
		add(Leaf.auto(obj));
	}
	
	public int size() {
		return values.size();
	}
	
	@Override
	public Iterator<Node> iterator() {
		return values.iterator();
	}

	public static Array of(Object...objs) {
		Array arr = new Array();
		for(Object obj : objs) {
			arr.add(obj);
		}
		
		return arr;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		builder.append("[");
		for(Node node : values) {
			builder.append(prefix);
			prefix = ", ";
			builder.append(node);
		}
		builder.append("]");
		
		return builder.toString();
	}
}
