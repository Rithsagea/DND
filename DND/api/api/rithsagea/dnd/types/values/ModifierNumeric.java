package api.rithsagea.dnd.types.values;

public abstract class ModifierNumeric extends Modifier {

	private int value;
	private int priority;
	
	public ModifierNumeric(int value, int priority) {
		this.value = value;
		this.priority = priority;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public int getPriority() {
		return priority;
	}
}
