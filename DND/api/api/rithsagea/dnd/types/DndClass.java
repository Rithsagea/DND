package api.rithsagea.dnd.types;

import api.rithsagea.dnd.event.Listener;

public class DndClass implements IndexedItem, Listener {
	
	private String id;
	
	public DndClass(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
