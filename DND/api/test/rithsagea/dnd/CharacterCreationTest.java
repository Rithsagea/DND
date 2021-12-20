package test.rithsagea.dnd;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.Registry;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Background;
import api.rithsagea.dnd.util.LanguageManager;

public class CharacterCreationTest {
	
	public static void main(String[] args) {
		LanguageManager lang = LanguageManager.getInstance();
		Registry registry = Registry.getInstance();
		TraitTest.registerTraits(registry);
		RaceTest.registerRaces(registry);
		
		CharacterSheet sheet = new CharacterSheet();
		
		sheet.setName("Lita");
		sheet.setPlayerName("Rithsagea");
		sheet.setBackground(Background.ACOLYTE);
		sheet.setAlignment(Alignment.TRUE_NEUTRAL);
		sheet.setInspiration(true);
		sheet.setBaseAbilityScores(15, 14, 13, 12, 10, 8);
		sheet.setExperience(Integer.MAX_VALUE);
		sheet.setRace(registry.getRace("Human"));
		
		sheet.refreshSheet();
		
		System.out.println(TestUtil.sheetToString(sheet));
		
		System.out.println("-=-=- Missing -=-=-");
		lang.missing().forEach(System.out::println);
	}
}
