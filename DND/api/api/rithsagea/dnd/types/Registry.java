package api.rithsagea.dnd.types;

import java.util.HashMap;
import java.util.Map;

import api.rithsagea.dnd.types.aspects.Feature;
import api.rithsagea.dnd.types.aspects.Trait;
import api.rithsagea.dnd.types.aspects.UniqueFeature;
import api.rithsagea.dnd.types.aspects.UniqueTrait;

public class Registry {
	private static Registry INSTANCE = new Registry();
	public static Registry getInstance() {
		return INSTANCE;
	}
	
	private Map<String, Trait> traits;
	private Map<String, AbstractRace> races;
	
	private Map<String, Feature> features;
	private Map<String, AbstractClass> classes;
	
	private Registry() {
		traits = new HashMap<>();
		races = new HashMap<>();
		
		features = new HashMap<>();
		classes = new HashMap<>();
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
	
	public AbstractRace getRace(String id) {
		return races.get(id);
	}

	public void registerFeature(Feature feature) {
		if(!features.containsKey(feature.getId())) {
			features.put(feature.getId(), feature);
			
			System.out.println("Registered Feature: " + feature.getId());
		}
	}
	
	public Feature getFeature(String id) {
		return features.get(id);
	}
	
	public void registerClass(AbstractClass clazz) {
		if(!classes.containsKey(clazz.getId())) {
			clazz.getFeatures().stream()
				.filter(f -> f instanceof UniqueFeature)
				.forEach(this::registerFeature);
			classes.put(clazz.getId(), clazz);
			
			System.out.println("Registered Class: " + clazz.getId());
		}
	}
	
	public AbstractClass getClass(String id) {
		return classes.get(id);
	}

}
