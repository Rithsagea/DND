package api.rithsagea.dnd.types.values;

public class ModifierAdd extends ModifierNumeric {

	public ModifierAdd(int value, int priority) {
		super(value, priority);
	}
	
	@Override
	public int modify(int base) {
		return base + getValue();
	}

	@Override
	public String getDisplay() {
		return (getValue() > 0 ? "Add: +" : "Subtract: ") + getValue();
	}

}
