package api.rithsagea.dnd.equipment;

import api.rithsagea.dnd.types.IndexedItem;

public abstract class InventoryItem implements IndexedItem {
	
	private int quantity;
	
	public InventoryItem(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public abstract int getWeight();
}
