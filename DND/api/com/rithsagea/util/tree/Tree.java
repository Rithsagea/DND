package com.rithsagea.util.tree;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Tree extends Node {
	
	private Map<String, Node> map;
	
	public Tree() {
		map = new TreeMap<>();
	}
	
	public void set(String key, Node node) {
		map.put(key, node);
	}
	
	public void set(String key, Object obj) {
		set(key, Leaf.auto(obj));
	}
	
	public Node get(String key) {
		return map.get(key);
	}
	
	public boolean has(String key) {
		return map.containsKey(key);
	}

	public int size() {
		return map.size();
	}
	
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		builder.append("{");
		for(Entry<String, Node> branch : map.entrySet()) {
			builder.append(prefix);
			prefix = ", ";
			builder.append('"');
			builder.append(branch.getKey());
			builder.append("\": ");
			builder.append(branch.getValue());
		}
		builder.append("}");
		
		return builder.toString();
	}

	public static Tree of(Map<?, ?> map) {
		Tree tree = new Tree();
		
		for(Entry<?, ?> entry : map.entrySet()) {
			//yes, class cast exception, should only pass in string keys
			tree.set((String) entry.getKey(), Leaf.auto(entry.getKey()));
		}
		
		return tree;
	}
}
