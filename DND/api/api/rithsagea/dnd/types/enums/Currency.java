package api.rithsagea.dnd.types.enums;

import com.rithsagea.util.WordUtil;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.types.LanguageManager;

public enum Currency implements IndexedItem {
	COPPER,
	SILVER,
	ELECTRUM,
	GOLD,
	PLATINUM;
	
	public String getUnitLabel() {
		return LanguageManager.getInstance().get(this, KeyConstants.UNIT_LABEL);
	}
	
	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
