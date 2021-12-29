package api.rithsagea.dnd.types;

import java.util.Set;

import api.rithsagea.dnd.types.aspects.Trait;

public interface AbstractRace extends IndexedItem {
	public Set<Trait> getTraits();
}
