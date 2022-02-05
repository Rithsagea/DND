package api.rithsagea.dnd.types.enums;

import com.rithsagea.util.WordUtil;

import api.rithsagea.dnd.types.IndexedItem;

public enum Condition implements IndexedItem {
	BLINDED,
	CHARMED,
	DEAFENED,
	EXHAUSTION,
	FRIGHTENED,
	GRAPPLED,
	INCAPACITATED,
	INVISIBLE,
	PARALZYED,
	PETRIFIED,
	POISONED,
	PRONE,
	RESTRAINED,
	STUNNED,
	UNCONSCIOUS;
	
	public String toString() {
		return getName();
	}
	
	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
}
