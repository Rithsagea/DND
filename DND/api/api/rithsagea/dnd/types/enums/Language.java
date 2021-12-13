package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.util.WordUtil;

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
