package api.rithsagea.dnd.types.values;

public class ModifierBase extends ModifierNumeric {
	
	public ModifierBase(int value, int priority) {
		super(value, priority);
	}

	@Override
	public int modify(int base) {
		return getValue();
	}

	@Override
	public int getPriority() {
		return 100;
	}

	@Override
	public String getDisplay() {
		return "Base: " + getValue();
	}
}
