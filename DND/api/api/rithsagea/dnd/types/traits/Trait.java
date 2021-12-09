package api.rithsagea.dnd.types.traits;

public abstract class Trait {
	public abstract String getDesc();
	public abstract String getName();
	
	public String toString() {
		return getName() + ". " + getDesc();
	}
}
