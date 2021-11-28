package com.rithsagea.dnd.api.types.extras;

import java.util.Arrays;
import java.util.List;

public class SpellcastingSlots {
	public int cantripsKnown;
	public int spellsKnown;
	public int spellSlotsLevel1;
	public int spellSlotsLevel2;
	public int spellSlotsLevel3;
	public int spellSlotsLevel4;
	public int spellSlotsLevel5;
	public int spellSlotsLevel6;
	public int spellSlotsLevel7;
	public int spellSlotsLevel8;
	public int spellSlotsLevel9;
	
	public List<Integer> getSlots() {
		return Arrays.asList(
				spellSlotsLevel1,
				spellSlotsLevel2,
				spellSlotsLevel3,
				spellSlotsLevel4,
				spellSlotsLevel5,
				spellSlotsLevel6,
				spellSlotsLevel7,
				spellSlotsLevel8,
				spellSlotsLevel9);
	}
}
