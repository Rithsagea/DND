package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.types.DndRace;

public abstract class UniqueTrait extends Trait {
	private DndRace parent;
	
	public UniqueTrait(DndRace parent) {
		this.parent = parent;
	}
	
	public DndRace getParent() {
		return parent;
	}
	
	@Override
	public String getId() {
		return parent.getId() + "." + getSubId();
	}
	
	public abstract String getSubId();
}
