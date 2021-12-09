package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.LanguageManager;
import api.rithsagea.dnd.util.WordUtil;

public enum Ability implements IndexedItem {
	STRENGTH,
	DEXTERITY,
	CONSTITUTION,
	INTELLIGENCE,
	WISDOM,
	CHARISMA;
	
	public String getShortName() {
		return LanguageManager.getInstance().get(this, KeyConstants.SHORT_NAME);
	}
	
	public String toString() {
		return getShortName();
	}

	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
}
