package api.rithsagea.dnd.types;

import com.rithsagea.util.event.Listener;

import api.rithsagea.dnd.character.CharacterSheet;

public abstract class DndClass implements IndexedItem, Listener {
	
	private String id;
	
	public DndClass(String id) {
		this.id = id;
	}
	
	public abstract void onLoad(CharacterSheet sheet);
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
