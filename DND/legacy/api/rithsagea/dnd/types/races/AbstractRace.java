package api.rithsagea.dnd.types.races;

import java.util.Set;

import com.rithsagea.util.event.Listener;

import api.rithsagea.dnd.types.IndexedItem;

public interface AbstractRace extends IndexedItem, Listener {
	public Set<Trait> getTraits();
}
