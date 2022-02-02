package api.rithsagea.dnd.types.enums;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.rithsagea.util.DataUtil;
import com.rithsagea.util.WordUtil;

import api.rithsagea.dnd.types.IndexedItem;

public enum Equipment implements IndexedItem {
	///Armor
	ARMOR,
	LIGHT_ARMOR,
	MEDIUM_ARMOR,
	SHIELDS,
	
	///Weapons
	SIMPLE_WEAPONS,
	MARTIAL_WEAPONS,
	
	FIREARMS,
	
	///Tools
	THIEVES_TOOLS,
	
	//Artisan Tools
	ALCHEMISTS_SUPPLIES,
	BREWERS_SUPPLIES,
	CALLIGRAPHERS_SUPPLIES,
	CARPENTERS_TOOLS,
	COBBLERS_TOOLS,
	COOKS_UTENSILS,
	GLASSBLOWERS_TOOLS,
	JEWELERS_TOOLS,
	LEATHERWORKERS_TOOLS,
	MASONS_TOOLS,
	PAINTERS_SUPPLIES,
	POTTERS_TOOLS,
	SMITHS_TOOLS,
	TINKERS_TOOLS,
	WEAVERS_TOOLS,
	WOODCARVERS_TOOLS
	;
	
	public static final Set<Equipment> ARTISANS_TOOLS = Collections.unmodifiableSet(new TreeSet<>(DataUtil.set(
			ALCHEMISTS_SUPPLIES,
			BREWERS_SUPPLIES,
			CALLIGRAPHERS_SUPPLIES,
			CARPENTERS_TOOLS,
			COBBLERS_TOOLS,
			COOKS_UTENSILS,
			GLASSBLOWERS_TOOLS,
			JEWELERS_TOOLS,
			LEATHERWORKERS_TOOLS,
			MASONS_TOOLS,
			PAINTERS_SUPPLIES,
			POTTERS_TOOLS,
			SMITHS_TOOLS,
			TINKERS_TOOLS,
			WEAVERS_TOOLS,
			WOODCARVERS_TOOLS
	)));

	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
