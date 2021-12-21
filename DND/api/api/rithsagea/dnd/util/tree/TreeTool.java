package api.rithsagea.dnd.util.tree;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import api.rithsagea.dnd.util.tree.Leaf.ObjectLeaf;

public class TreeTool {
	public static final String CLASS_FIELD = "$class";
	public static final char TREE_START_TOKEN = '{';
	public static final char TREE_END_TOKEN = '}';
	public static final char ARRAY_START_TOKEN = '[';
	public static final char STRING_START_TOKEN = '"';
	public static final char CHAR_START_TOKEN = '\'';
	
	public static boolean validateModifiers(int modifiers) {
		return !(Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers));
	}
	
	public static Map<String, Field> getFields(Class<?> clazz) {
		Map<String, Field> fields = new TreeMap<>();
		
		while(!Object.class.equals(clazz)) {
			for(Field field : clazz.getFields()) {
				String name = field.getName();
				
				if(!fields.containsKey(name) && !CLASS_FIELD.equals(name))
					fields.put(name, field);
			}
			
			clazz = clazz.getSuperclass();
		}
		
		return fields;
	}
	
	public static Node serialize(Object obj) {
		Node n = Node.auto(obj);
		
		if(n instanceof Array) {
			Array arr = (Array) n;
			for(int i = 0; i < arr.size(); i++) {
				if(arr.get(i) instanceof ObjectLeaf)
					arr.set(i, serialize(arr.get(i)));
			}
		}
		
		if(n instanceof ObjectLeaf) obj = n.asObject();
		else return n;
		
		
		Tree tree = new Tree();
		tree.set(CLASS_FIELD, obj.getClass().getName());
		Map<String, Field> fields = getFields(obj.getClass());
		
		for(Entry<String, Field> entry : fields.entrySet()) {
			if(!validateModifiers(entry.getValue().getModifiers())) continue;
			
			try {
				tree.set(entry.getKey(), serialize(entry.getValue().get(obj)));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return tree;
	}

	public static Object deserialize(Node node) {
		if(node instanceof Leaf) {
			return ((Leaf<?>) node).get();
		}
		
		if(node instanceof Array) {
			List<Object> list = new ArrayList<>();
			for(Node n : (Array) node) {
				list.add(deserialize(n));
			}
			
			return list;
		}
		
		Tree tree = (Tree) node;
		Object obj = null;
		try {
			Class<?> clazz = Class.forName(tree.get(CLASS_FIELD).asString());
			Map<String, Field> fields = getFields(clazz);
			obj = clazz.getConstructor().newInstance();
			
			for(String key : tree.keySet()) {
				Field f = fields.get(key);
				if(f != null) {
					f.set(obj, deserialize(tree.get(key)));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	public static String toString(Object obj) {
		return toString(serialize(obj));
	}
	
	public static String toString(Node node) {
		return node.toString();
	}
	
	//TODO deserialize from string
}
