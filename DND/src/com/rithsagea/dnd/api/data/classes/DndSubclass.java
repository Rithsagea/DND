package com.rithsagea.dnd.api.data.classes;

import java.util.HashMap;
import java.util.List;

import com.rithsagea.dnd.api.data.DndItem;

public class DndSubclass extends DndItem {
	public String parentClass;
	public String name;
	public String flavor;
	public String description;
	
	public HashMap<String, Integer> spells;
	public List<DndSubclassLevel> levels;
}
