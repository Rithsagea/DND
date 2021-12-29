package api.rithsagea.dnd.types.enums;

import com.rithsagea.util.WordUtil;

import api.rithsagea.dnd.types.IndexedItem;

public enum Equipment implements IndexedItem {
	//Armor
	ARMOR,
	SHIELDS,
	
	//Weapons
	SIMPLE_WEAPONS,
	MARTIAL_WEAPONS,
	
	//Tools
	;

	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
}
