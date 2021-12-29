package api.rithsagea.dnd.types;

import java.util.Set;

import com.rithsagea.util.dice.Dice;

import api.rithsagea.dnd.types.aspects.Feature;

public interface AbstractClass extends IndexedItem {
	public Set<Feature> getFeatures();
	public Dice getHitDice();
}
