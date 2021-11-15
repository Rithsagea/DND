package com.rithsagea.dnd.api5e.data.equipment;

import com.rithsagea.dnd.api5e.data.DndItem;
import com.rithsagea.dnd.api5e.data.extra.CoinQuantity;

public class Equipment extends DndItem {
	public String name;
	public String category;
	public String description;
	public int weight;
	
	public CoinQuantity cost;
}
