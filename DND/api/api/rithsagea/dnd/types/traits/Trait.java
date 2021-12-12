package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.IndexedItem;

public interface Trait extends IndexedItem, Listener {
	public String getDesc();
}
