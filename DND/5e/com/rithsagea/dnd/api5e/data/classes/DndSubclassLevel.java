package com.rithsagea.dnd.api5e.data.classes;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api5e.data.IndexedItem;

public class DndSubclassLevel extends IndexedItem {
	
	public int level;
	public List<String> features;
	public Map<String, Integer> specific;
}
