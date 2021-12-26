package api.rithsagea.dnd.character;

import api.rithsagea.dnd.types.DndClass;

public class CharacterClass {
	private DndClass classType;
	private int level;
	
	public CharacterClass(DndClass classType, int level) {
		this.classType = classType;
		this.level = level;
	}
	
	public CharacterClass(DndClass classType) {
		this(classType, 1);
	}
	
	public DndClass getClassType() {
		return classType;
	}
	
	public int getLevel() {
		return level;
	}
}
