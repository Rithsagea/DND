package api.rithsagea.dnd.types.values;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import api.rithsagea.dnd.types.IndexedItem;

public class Value {
	private Map<Modifier, IndexedItem> modifiers;
	private int value;
	
	public Value() {
		modifiers = new TreeMap<>();
		value = 0;
	}
	
	private void recalcModifiers() {
		value = 0;
		
		for(Modifier mod : modifiers.keySet()) {
			value = mod.modify(value);
		}
	}
	
	public void addModifier(Modifier mod, IndexedItem item) {
		modifiers.put(mod, item);
		recalcModifiers();
	}
	
	public void removeModifier(Modifier mod) {
		modifiers.remove(mod);
		recalcModifiers();
	}
	
	
	public Map<Modifier, IndexedItem> getModifiers() {
		return Collections.unmodifiableMap(modifiers);
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(value);
		builder.append(": ");
		
		String prefix = "";
		for(Entry<Modifier, IndexedItem> entry : modifiers.entrySet()) {
			builder.append(prefix);
			builder.append(entry.getValue() + " -> " + entry.getKey());
			prefix = " | ";
		}
		return builder.toString();
	}
}
