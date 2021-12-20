package api.rithsagea.dnd.types;

import java.util.HashMap;
import java.util.Map;

import api.rithsagea.dnd.types.traits.Trait;
import api.rithsagea.dnd.types.traits.UniqueTrait;

public class Registry {
	private static Registry INSTANCE = new Registry();
	public static Registry getInstance() {
		return INSTANCE;
	}
	
	private Map<String, Trait> traits;
	private Map<String, DndRace> races;
	
	private Registry() {
		traits = new HashMap<>();
		races = new HashMap<>();
	}
	
	public void registerTrait(Trait trait) {
		if(!traits.containsKey(trait.getId())) {
			traits.put(trait.getId(), trait);
			
			System.out.println("Registered Trait: " + trait.getId());
		}
	}
	
	public Trait getTrait(String id) {
		return traits.get(id);
	}
	
	public void registerRace(DndRace race) {
		if(!races.containsKey(race.getId())) {
			race.getTraits().stream()
				.filter(t -> t instanceof UniqueTrait)
				.forEach(this::registerTrait);
			races.put(race.getId(), race);
			
			System.out.println("Registered Race: " + race.getId());
		}
	}
	
	public DndRace getRace(String id) {
		return races.get(id);
	}
}
