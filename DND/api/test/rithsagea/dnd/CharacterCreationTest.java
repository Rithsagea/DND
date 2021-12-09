package test.rithsagea.dnd;

import java.util.Map;
import java.util.Set;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Size;
import api.rithsagea.dnd.types.traits.AbilityScoreTrait;
import api.rithsagea.dnd.types.traits.DescriptionTrait;
import api.rithsagea.dnd.types.traits.Trait;
import api.rithsagea.dnd.util.Dice.Die;
import api.rithsagea.dnd.util.ReflectUtil;
import api.rithsagea.dnd.util.TextManager;
import api.rithsagea.dnd.util.WordUtil;

public class CharacterCreationTest {
	public static CharacterSheet createVarikane() {
		CharacterSheet sheet = new CharacterSheet();
		
		sheet.setName("Varikane");
		sheet.setAlignment(Alignment.TRUE_NEUTRAL);
		
		sheet.setAbilityScores(8, 14, 10, 15, 14, 14);
		sheet.addHitDie(new Die(6, 1));
		sheet.addHitDie(new Die(6, 1));
		sheet.setHitPoints(Integer.MAX_VALUE);
		
		return sheet;
	}
	
	public static CharacterSheet createRithsagea() {
		CharacterSheet sheet = new CharacterSheet();
		
		sheet.setName("Rithsagea");
		sheet.setAlignment(Alignment.TRUE_NEUTRAL);
		
		sheet.setAbilityScores(18, 18, 18, 18, 18, 18);
		
		return sheet;
	}
	
	public static DndRace createYama() {
		DndRace race = new DndRace("Yama");
		
		Set<Trait> traits = ReflectUtil.getField(race, "traits", Set.class);
		traits.add(new AbilityScoreTrait(Map.of(
				Ability.WISDOM, 1,
				Ability.CHARISMA, 1)));
		
		traits.add(new DescriptionTrait("Age", "Yama are near immortal; they do not age, though they can still die in combat."));
		traits.add(new DescriptionTrait("Alignment", "Yama are always either ", Alignment.LAWFUL_NEUTRAL, " or ", Alignment.TRUE_NEUTRAL,"; they are almost never good, evil, or chaotic alignments. Lawful alignments are much more common among them."));
		traits.add(new DescriptionTrait("Size", "Yama vary drastically in stature, with the shortest standing only just over 5 feet tall and the tallest brushing just short of 8 feet in height. Regardless of where you stand in this range, your size is ", Size.MEDIUM, "."));
		traits.add(new DescriptionTrait("Speed", "Your base walking speed is 30 feet."));
		return race;
	}
	
	public static void main(String[] args) {
		TextManager.getInstance().load();
		CharacterSheet rith = createRithsagea();
		DndRace yama = createYama();
		
//		System.out.println(TestUtil.toString(rith));
		System.out.println(TestUtil.toString(yama)); 
		
		TextManager.getInstance().save();
	}
}
