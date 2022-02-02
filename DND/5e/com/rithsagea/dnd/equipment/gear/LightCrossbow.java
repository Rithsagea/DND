package com.rithsagea.dnd.equipment.gear;

import java.util.Collections;
import java.util.Set;

import com.rithsagea.util.DataUtil;

import api.rithsagea.dnd.equipment.Weapon;
import api.rithsagea.dnd.equipment.properties.Ammunition;
import api.rithsagea.dnd.equipment.properties.Loading;
import api.rithsagea.dnd.equipment.properties.TwoHanded;
import api.rithsagea.dnd.types.enums.Equipment;

public class LightCrossbow extends Weapon implements Ammunition, Loading, TwoHanded {

	private static final Set<Equipment> PROFICIENCIES = Collections.unmodifiableSet(DataUtil.set(
			Equipment.SIMPLE_WEAPONS));
	
	public LightCrossbow(int quantity) {
		super(quantity);
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public Set<Equipment> getProficiencies() {
		return PROFICIENCIES;
	}
	
}
