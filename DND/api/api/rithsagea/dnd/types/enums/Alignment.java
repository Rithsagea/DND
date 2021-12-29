package api.rithsagea.dnd.types.enums;

import com.rithsagea.util.WordUtil;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.types.LanguageManager;

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
