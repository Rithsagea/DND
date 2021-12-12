package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.IndexedItem;

public abstract class Trait implements IndexedItem, Listener {
	public abstract String getDesc();
	
	public String toString() {
		return getName() + ". " + getDesc();
	}
}
