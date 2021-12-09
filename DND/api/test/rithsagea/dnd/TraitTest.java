package test.rithsagea.dnd;

import java.util.Map;

import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.traits.AbilityScoreTrait;
import api.rithsagea.dnd.util.TextManager;

public class TraitTest {
	public static void main(String[] args) {
		TextManager.getInstance().load();
		
		AbilityScoreTrait trait = new AbilityScoreTrait(
				Map.of(
						Ability.INTELLIGENCE, 3,
						Ability.CONSTITUTION, 2,
						Ability.WISDOM, 1,
						Ability.CHARISMA, 1));
		
		System.out.println(trait.getName() + ": " + trait.getDesc());
	}
}
