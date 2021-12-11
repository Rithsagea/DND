package api.rithsagea.dnd.types.values;

public class ModifierMin extends ModifierNumeric {

	public ModifierMin(int value, int priority) {
		super(value, priority);
	}

	@Override
	public int modify(int base) {
		return Math.max(base, getValue());
	}

	@Override
	public String getDisplay() {
		return "Min: " + getValue();
	}

}
