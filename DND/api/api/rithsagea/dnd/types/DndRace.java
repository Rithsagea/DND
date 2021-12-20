package api.rithsagea.dnd.types;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.event.Listener;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.traits.AbilityScoreIncreaseTrait;
import api.rithsagea.dnd.types.traits.DescriptionTrait;
import api.rithsagea.dnd.types.traits.DescriptionTrait.DescriptionType;
import api.rithsagea.dnd.types.traits.SpeedTrait;
import api.rithsagea.dnd.types.traits.Trait;

public class DndRace implements IndexedItem, Listener {
	
	private String id;
	private Set<Trait> traits;
	
	public DndRace(String id, int speed, Map<Ability, Integer> abilityScores) {
		this.id = id;
		traits = new TreeSet<>();
		
		addTrait(new DescriptionTrait(this, DescriptionType.AGE));
		addTrait(new DescriptionTrait(this, DescriptionType.ALIGNMENT));
		addTrait(new DescriptionTrait(this, DescriptionType.SIZE));
		addTrait(new DescriptionTrait(this, DescriptionType.LANGUAGE));
		addTrait(new SpeedTrait(this, speed));
		addTrait(new AbilityScoreIncreaseTrait(this, abilityScores));
	}
	
	protected void addTrait(Trait trait) {
		traits.add(trait);
	}
	
	public Set<Trait> getTraits() {
		return Collections.unmodifiableSet(traits);
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
