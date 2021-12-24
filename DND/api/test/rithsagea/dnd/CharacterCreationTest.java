package test.rithsagea.dnd;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.Registry;
import api.rithsagea.dnd.util.LanguageManager;

public class CharacterCreationTest {
	
	public static void main(String[] args) {
		LanguageManager lang = LanguageManager.getInstance();
		Registry registry = Registry.getInstance();
		TraitTest.registerTraits(registry);
		RaceTest.registerRaces(registry);
		
		CharacterSheet sheet = new CharacterSheet();
		
		sheet.refresh();
		
		System.out.println("-=-=- Missing -=-=-");
		lang.missing().forEach(System.out::println);
	}
}
