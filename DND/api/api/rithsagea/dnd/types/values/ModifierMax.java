package api.rithsagea.dnd.types.values;

public class ModifierMax extends ModifierNumeric {

	public ModifierMax(int value, int priority) {
		super(value, priority);
	}

	@Override
	public int modify(int base) {
		return Math.min(base, getValue());
	}

	@Override
	public String getDisplay() {
		return "Max: " + getValue();
	}
}
