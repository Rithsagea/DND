package api.rithsagea.dnd.character;

import api.rithsagea.dnd.types.AbstractClass;

public class CharacterClass {
	private AbstractClass classType;
	private int level;
	
	public CharacterClass(AbstractClass classType, int level) {
		this.classType = classType;
		this.level = level;
	}
	
	public CharacterClass(AbstractClass classType) {
		this(classType, 1);
	}
	
	public AbstractClass getClassType() {
		return classType;
	}
	
	public int getLevel() {
		return level;
	}
}
