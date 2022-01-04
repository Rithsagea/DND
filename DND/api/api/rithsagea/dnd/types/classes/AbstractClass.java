package api.rithsagea.dnd.types.classes;

import java.util.Set;

import com.rithsagea.util.event.Listener;

import api.rithsagea.dnd.types.IndexedItem;

public interface AbstractClass extends IndexedItem, Listener {
	public Set<Feature> getFeatures();
	
	public void levelUp();
	public int getLevel();
	public boolean isMulticlass();
}
