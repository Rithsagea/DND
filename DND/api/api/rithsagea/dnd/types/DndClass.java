package api.rithsagea.dnd.types;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import com.rithsagea.util.dice.Dice;

import api.rithsagea.dnd.types.aspects.Feature;

public class DndClass implements AbstractClass {

	private String id;
	private Dice hitDice;
	private Set<Feature> features;
	
	public DndClass(String id, Dice hitDice) {
		this.id = id;
		this.hitDice = hitDice.clone();
		
		features = new TreeSet<>();
	}
	
	protected void addFeature(Feature feature) {
		features.add(feature);
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public Dice getHitDice() {
		return hitDice;
	}
	
	@Override
	public Set<Feature> getFeatures() {
		return Collections.unmodifiableSet(features);
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
