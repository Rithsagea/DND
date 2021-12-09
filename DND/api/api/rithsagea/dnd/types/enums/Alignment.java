package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.TextManager;
import api.rithsagea.dnd.util.WordUtil;

public enum Alignment implements IndexedItem {
	LAWFUL_GOOD,
	NEUTRAL_GOOD,
	CHAOTIC_GOOD,
	
	LAWFUL_NEUTRAL,
	TRUE_NEUTRAL,
	CHAOTIC_NEUTRAL,
	
	LAWFUL_EVIL,
	NEUTRAL_EVIL,
	CHAOTIC_EVIL;
	
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
