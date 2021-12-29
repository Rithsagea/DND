package api.rithsagea.dnd.types.enums;

import com.rithsagea.util.WordUtil;

import api.rithsagea.dnd.types.IndexedItem;

public enum Language implements IndexedItem {
	COMMON,
	DWARVISH,
	ELVISH,
	GIANT,
	GNOMISH,
	GOBLIN,
	HALFLING,
	ORC,
	ABYSSAL,
	CELESTIAL,
	DRACONIC,
	DEEP_SPEECH,
	INFERNAL,
	PRIMORDIAL,
	SYLVAN,
	UNDERCOMMON;
	
	public String toString() {
		return getName();
	}
	
	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
	
}
