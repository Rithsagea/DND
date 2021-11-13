package com.rithsagea.dnd.api.data.classes;

import java.util.List;
import java.util.Map;

import com.rithsagea.dnd.api.data.DndItem;

public class DndSubclassLevel extends DndItem {
	
	public int level;
	public List<String> features;
	public Map<String, Integer> specific;
}
