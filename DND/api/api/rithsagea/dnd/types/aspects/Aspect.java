package api.rithsagea.dnd.character;

import com.rithsagea.util.event.Listener;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.LanguageManager;

public abstract class Aspect implements IndexedItem, Listener, Comparable<Aspect> {
	
	public String getDescription() {
		return LanguageManager.getInstance().get(this, KeyConstants.DESCRIPTION);
	}
	
	@Override
	public String toString() {
		return getName() + ". " + getDescription();
	}
	
	@Override
	public int compareTo(Aspect a) {
		return getName().compareTo(a.getName());
	}
}
