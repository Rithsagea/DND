package com.rithsagea.dnd.api.types;

import java.util.List;

public class DndSubclass extends IndexedItem {
	
	public String name;
	public String flavor;
	public String description;
	
	public String parentClass;
	public List<DndSubclassLevel> levels;
}
