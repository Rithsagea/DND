package api.rithsagea.dnd.types;

import java.util.Set;

import com.rithsagea.util.event.Listener;

import api.rithsagea.dnd.features.Feature;
import api.rithsagea.dnd.features.HitPointFeature;

public abstract class DndClass implements IndexedItem, Listener {
	
	private String id;
	private Set<Feature> features;
	
	public DndClass(String id) {
		this.id = id;
		
		features.add(getHitPoints());
	}
	
	public abstract HitPointFeature getHitPoints();
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
