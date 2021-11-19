package com.rithsagea.dnd.api5e.data.classes;

import java.util.HashMap;
import java.util.List;

import com.rithsagea.dnd.api.types.IndexedItem;

public class DndSubclass extends IndexedItem {
	public String parentClass;
	public String name;
	public String flavor;
	public String description;
	
	public HashMap<String, Integer> spells;
	public List<DndSubclassLevel> levels;
}
