package com.rithsagea.dnd.api5e.data.classes;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.types.IndexedItem;

public class Dnd5eSubclassLevel extends IndexedItem {
	
	public int level;
	public List<String> features;
	public Map<String, Integer> specific;
}
