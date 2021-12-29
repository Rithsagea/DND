package api.rithsagea.dnd.types;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.types.aspects.Trait;
import api.rithsagea.dnd.types.enums.Ability;

public class DndRace implements AbstractRace {
	
	private String id;
	private Set<Trait> traits;
	
	public DndRace(String id, int speed, Map<Ability, Integer> abilityScores) {
		this.id = id;
		traits = new TreeSet<>();
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
