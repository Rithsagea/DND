package api.rithsagea.dnd.types.classes;

import com.rithsagea.util.dice.Dice;
import com.rithsagea.util.event.EventHandler;

import api.rithsagea.dnd.character.events.update.UpdateMaxHitPointEvent;
import api.rithsagea.dnd.types.enums.Ability;

public class HitPointFeature extends UniqueFeature {
	private Dice hitDice;
	
	public HitPointFeature(AbstractClass parent, Dice hitDice) {
		super(parent);
		this.hitDice = hitDice;
	}
	
	@EventHandler
	public void onUpdateMaxHitPoint(UpdateMaxHitPointEvent e) {
		int conMod = e.getSheet().getAbilityModifier(Ability.CONSTITUTION);
		int half = hitDice.getValue() / 2 + 1;
		
		for(int x = 0; x < getParent().getLevel(); x++) {
			e.addHitDie(hitDice);
			e.add(half + conMod);
		}
		
		if(!getParent().isMulticlass()) {
			e.add(half - 2);
		}
	}

	@Override
	public String getSubId() {
		return "HitPoint";
	}
}
