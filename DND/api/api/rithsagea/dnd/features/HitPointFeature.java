package api.rithsagea.dnd.features;

import api.rithsagea.dnd.dice.Dice;
import api.rithsagea.dnd.types.DndClass;

public class HitPointFeature extends UniqueFeature {

	private Dice hitDice;
	
	public HitPointFeature(DndClass parent, Dice hitDice) {
		super(parent);
		
		this.hitDice = hitDice;
	}

	public Dice getHitDice() {
		return hitDice;
	}
	
	@Override
	public String getSubId() {
		return "HitPoint";
	}
}
