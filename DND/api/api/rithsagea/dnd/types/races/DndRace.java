package api.rithsagea.dnd.types.races;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class DndRace implements AbstractRace {
	
	private Set<Trait> traits;
	
	public DndRace() {
		traits = new HashSet<>();
	}
	
	protected void addTrait(Trait trait) {
		traits.add(trait);
	}
	
	protected void addTraits(Collection<Trait> traits) {
		traits.forEach(this::addTrait);
	}
	
	protected void addTraits(Trait... traits) {
		addTraits(Arrays.asList(traits));
	}

	@Override
	public Set<Trait> getTraits() {
		return traits;
	}
	
}
