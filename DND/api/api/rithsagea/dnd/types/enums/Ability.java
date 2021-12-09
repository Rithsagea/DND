package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.WordUtil;
import api.rithsagea.dnd.util.TextManager;

public enum Ability implements IndexedItem {
	STRENGTH,
	DEXTERITY,
	CONSTITUTION,
	INTELLIGENCE,
	WISDOM,
	CHARISMA;
	
	public String getShortName() {
		return TextManager.getInstance().getMessage(this, KeyConstants.SHORT_NAME);
	}
	
	public String getName() {
		return TextManager.getInstance().getMessage(this, KeyConstants.NAME);
	}
	
	public String toString() {
		return getShortName();
	}

	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
}
