package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.util.WordUtil;

public enum EquipmentProficiency implements IndexedItem {
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
