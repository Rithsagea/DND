package api.rithsagea.dnd.types;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import api.rithsagea.dnd.util.TextManager;

public class DndRace implements IndexedItem {
	
	private String id;
	private Set<Trait> traits;
	
	public DndRace(String id) {
		this.id = id;
		
		traits = new LinkedHashSet<>();
	}
	
	public Set<Trait> getTraits() {
		return Collections.unmodifiableSet(traits);
	}

	public String getName() {
		return TextManager.getInstance().getMessage(this, KeyConstants.NAME);
	}
	
	@Override
	public String getId() {
		return id;
	}
}
