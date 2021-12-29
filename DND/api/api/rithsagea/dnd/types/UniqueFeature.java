package api.rithsagea.dnd.features;

import api.rithsagea.dnd.types.DndClass;

public abstract class UniqueFeature extends Feature {
	
	private DndClass parent;
	
	public UniqueFeature(DndClass parent) {
		this.parent = parent;
	}
	
	public DndClass getParent() {
		return parent;
	}
	
	@Override
	public String getId() {
		return parent.getId() + "." + getSubId();
	}
	
	public abstract String getSubId();
}
