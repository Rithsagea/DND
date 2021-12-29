package api.rithsagea.dnd.types.enums;

import com.rithsagea.util.WordUtil;

import api.rithsagea.dnd.types.IndexedItem;

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
	
	public String toString() {
		return getName();
	}
	
	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}

}
