package test.rithsagea.dnd;

import java.util.HashSet;
import java.util.Set;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.traits.DescriptionTrait;
import api.rithsagea.dnd.types.traits.DescriptionTrait.DescriptionType;
import api.rithsagea.dnd.types.traits.Trait;
import api.rithsagea.dnd.util.LanguageManager;

public class CharacterCreationTest {
	public static void main(String[] args) {
		LanguageManager lang = LanguageManager.getInstance();
		CharacterSheet sheet = new CharacterSheet();
		
		sheet.setName("Rithsagea");
		sheet.setAlignment(Alignment.TRUE_NEUTRAL);
		sheet.setInspiration(true);
		sheet.setBaseAbilityScores(15, 14, 13, 12, 10, 8);
		
		sheet.refreshSheet();
		
		Set<Trait> traits = new HashSet<>();
		traits.add(new DescriptionTrait("Enma", DescriptionType.AGE));
		traits.add(new DescriptionTrait("Enma", DescriptionType.ALIGNMENT));
		traits.add(new DescriptionTrait("Enma", DescriptionType.SIZE));
		
//		System.out.println(TestUtil.toString(sheet));
		for(Trait trait : traits) {
			System.out.println(trait.getName() + ". " + trait.getDesc());
		}
		
		System.out.println("-=-=- Missing -=-=-");
		lang.missing().forEach(System.out::println);
	}
}
