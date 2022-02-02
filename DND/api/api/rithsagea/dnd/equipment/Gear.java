package api.rithsagea.dnd.equipment;

import java.util.Set;

import api.rithsagea.dnd.types.enums.Equipment;

public abstract class Gear extends InventoryItem {

	public Gear(int quantity) {
		super(quantity);
	}
	
	public abstract Set<Equipment> getProficiencies();

}
