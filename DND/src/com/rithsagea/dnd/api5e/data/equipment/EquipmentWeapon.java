package com.rithsagea.dnd.api5e.data.equipment;

import java.util.List;

public class EquipmentWeapon extends Equipment {
	public String weaponCategory;
	public String weaponRange;
	public String categoryRange;
	
	public String damageDice;
	public String damageType;
	
	public int normalRange;
	public int longRange;
	
	public int normalThrowRange;
	public int longThrowRange;
	
	public List<String> properties;
}
