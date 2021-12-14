package api.rithsagea.dnd.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.character.UpdateSheetEvent.LoadTraitsEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.traits.DescriptionTrait;
import api.rithsagea.dnd.types.traits.DescriptionTrait.DescriptionType;
import api.rithsagea.dnd.types.traits.Trait;

public class DndRace implements IndexedItem, Listener {
	
	private String id;
	private Set<Trait> traits;
	
	public DndRace(String id) {
		this.id = id;
		traits = new TreeSet<>();
		
		addTraits(
				new DescriptionTrait(this, DescriptionType.AGE),
				new DescriptionTrait(this, DescriptionType.ALIGNMENT),
				new DescriptionTrait(this, DescriptionType.SIZE));
	}
	
	protected void addTrait(Trait trait) {
		traits.add(trait);
	}
	
	protected void addTraits(Trait...traits) {
		this.traits.addAll(Arrays.asList(traits));
	}
	
	public Set<Trait> getTraits() {
		return Collections.unmodifiableSet(traits);
	}
	
	@EventHandler
	public void onLoadTraits(LoadTraitsEvent e) {
		traits.forEach(e::addTrait);
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
