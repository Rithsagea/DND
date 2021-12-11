package api.rithsagea.dnd.types.values;

public class ModifierMultiply extends ModifierNumeric {

	public ModifierMultiply(int value, int priority) {
		super(value, priority);
	}

	@Override
	public int modify(int base) {
		return base * getValue();
	}

	@Override
	public String getDisplay() {
		return "Multiply: " + getValue();
	}
	
}
