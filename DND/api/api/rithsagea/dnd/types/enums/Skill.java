package api.rithsagea.dnd.types.enums;

import api.rithsagea.dnd.types.IndexedItem;
import api.rithsagea.dnd.types.KeyConstants;
import api.rithsagea.dnd.util.TextManager;
import api.rithsagea.dnd.util.WordUtil;

public enum Skill implements IndexedItem {
	ACROBATICS(Ability.DEXTERITY),
	ANIMAL_HANDLING(Ability.WISDOM),
	ARCANA(Ability.INTELLIGENCE),
	ATHLETICS(Ability.STRENGTH),
	DECEPTION(Ability.CHARISMA),
	HISTORY(Ability.INTELLIGENCE),
	INSIGHT(Ability.WISDOM),
	INTIMIDATION(Ability.CHARISMA),
	INVESTIGATION(Ability.INTELLIGENCE),
	MEDICINE(Ability.WISDOM),
	NATURE(Ability.INTELLIGENCE),
	PERCEPTION(Ability.WISDOM),
	PERFORMANCE(Ability.CHARISMA),
	PERSUASION(Ability.CHARISMA),
	RELIGION(Ability.INTELLIGENCE),
	SLEIGHT_OF_HAND(Ability.DEXTERITY),
	STEALTH(Ability.DEXTERITY),
	SURVIVAL(Ability.WISDOM);
	
	private Ability ability;
	
	private Skill(Ability ability) {
		this.ability = ability;
	}
	
	public Ability getAbility() {
		return ability;
	}
	
	public String getName() {
		return TextManager.getInstance().getMessage(this, KeyConstants.NAME);
	}
	
	public String toString() {
		return getName();
	}
	
	@Override
	public String getId() {
		return WordUtil.formatId(name());
	}
}
