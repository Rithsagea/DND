package api.rithsagea.dnd.util.choice;

import com.rithsagea.util.choice.Choice;

import api.rithsagea.dnd.types.IndexedItem;

public class IndexedChoice implements Choice {
	
	private IndexedItem item;
	
	public IndexedChoice(IndexedItem item) {
		this.item = item;
	}
	
	public IndexedItem getItem() {
		return item;
	}
	
	@Override
	public String toString() {
		return item.getName();
	}
}
