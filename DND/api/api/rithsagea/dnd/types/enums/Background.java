package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.util.WordUtil;

public enum Background implements IndexedItem {
	ACOLYTE,
	CHARLATAN,
	CRIMINAL,
	ENTERTAINER,
	FOLK_HERO,
	GLADIATOR, //Entertainer Variant
	GUILD_ARTISAN,
	HERMIT,
	NOBLE,
	OUTLANDER,
	SAGE,
	SAILOR,
	SOLDIER,
	URCHIN;
	
	public String toString() {
		return getName();
	}
	
	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
}
