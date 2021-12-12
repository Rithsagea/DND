package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.LanguageManager;

public abstract class Trait implements IndexedItem, Listener, Comparable<Trait> {
	public String getDescription() {
		return LanguageManager.getInstance().get(this, KeyConstants.DESCRIPTION);
	}
	
	@Override
	public String toString() {
		return getName() + ". " + getDescription();
	}
	
	@Override
	public int compareTo(Trait t) {
		return getName().compareTo(t.getName());
	}
}
