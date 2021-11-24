package com.rithsagea.dnd.api.types.extras;

public class ItemStack {
	
	public ItemStack() { }
	public ItemStack(String item, int count) { this.item = item; this.count = count; }
	
	public String item;
	public int count;
	
	public String toString() { return item + "*" + count; }
}
