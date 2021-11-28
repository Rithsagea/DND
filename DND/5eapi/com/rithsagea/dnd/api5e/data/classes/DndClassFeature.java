package com.rithsagea.dnd.api5e.data.classes;

import java.util.List;

import com.rithsagea.dnd.api.types.IndexedItem;

public class DndClassFeature extends IndexedItem {
	public String name;
	public String description;
	public String classId;
	public String subclassId;
	public int level;
	
	public String optionType;
	public int optionCount;
	public List<String> options;
}
