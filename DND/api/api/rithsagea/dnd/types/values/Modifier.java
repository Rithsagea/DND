package api.rithsagea.dnd.types.values;

public abstract class Modifier implements Comparable<Modifier> {
	public abstract int modify(int base);
	public abstract String getDisplay();
	
	public int getPriority() { return 0; }
	
	
	@Override
	public int compareTo(Modifier mod) {
		return mod.getPriority() - getPriority();
	}
	
	@Override
	public String toString() {
		return getDisplay();
	}
}
