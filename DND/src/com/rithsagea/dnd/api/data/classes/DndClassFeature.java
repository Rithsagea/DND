package com.rithsagea.dnd.api.data.classes;

import java.util.List;

import com.rithsagea.dnd.api.data.DndItem;

public class DndClassFeature extends DndItem {
	public String name;
	public String description;
	
	public String optionType;
	public int optionCount;
	public List<String> options;
}
