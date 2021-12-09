package api.rithsagea.dnd.types;

import api.rithsagea.dnd.util.LanguageManager;

public interface IndexedItem {
	public default String getName() {
		return LanguageManager.getInstance().get(this, KeyConstants.NAME);
	}
	
	public String getId();
}
