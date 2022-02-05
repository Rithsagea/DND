package api.rithsagea.dnd.character.events.update;

import com.rithsagea.util.dice.Dice;
import com.rithsagea.util.dice.Roll;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.character.events.UpdateFieldEvent;

public class UpdateMaxHitPointEvent extends UpdateFieldEvent {

	private Roll hitDice;
	
	public UpdateMaxHitPointEvent(CharacterSheet sheet, int value) {
		super(sheet, value);
		
		hitDice = new Roll();
	}
	
	public void addHitDie(Dice die) {
		hitDice.add(die);
	}
	
	public Roll getHitDice() {
		return hitDice.clone();
	}
}
