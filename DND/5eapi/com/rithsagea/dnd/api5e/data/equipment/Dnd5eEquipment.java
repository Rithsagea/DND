package com.rithsagea.dnd.api5e.data.equipment;

import com.rithsagea.dnd.api.types.IndexedItem;
import com.rithsagea.dnd.api5e.data.extra.CoinQuantity;

public class Dnd5eEquipment extends IndexedItem {
	public String name;
	public String category;
	public String description;
	public int weight;
	
	public CoinQuantity cost;
}
