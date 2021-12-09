package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.TextManager;
import api.rithsagea.dnd.util.WordUtil;

public enum Size implements IndexedItem {
	//https://www.dandwiki.com/wiki/SRD:Movement,_Position,_and_Distance#Big_and_Little_Creatures_in_Combat
	
	FINE,
	DIMINUITIVE,
	TINY,
	SMALL,
	MEDIUM,
	LARGE,
	HUGE,
	GARGANTUAN,
	COLOSSAL;

	public String getName() {
		return TextManager.getInstance().getMessage(this, KeyConstants.NAME);
	}
	
	public String toString() {
		return getName();
	}
	
	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}

}
