package test.rithsagea.dnd;

import com.rithsagea.dnd.race.human.HumanRace;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.util.LanguageManager;

public class CharacterCreationTest {
	public static void main(String[] args) {
		LanguageManager lang = LanguageManager.getInstance();
		
		DndRace race = new HumanRace();
		
		CharacterSheet sheet = new CharacterSheet();
		
		sheet.setName("Rithsagea");
		sheet.setAlignment(Alignment.TRUE_NEUTRAL);
		sheet.setInspiration(true);
		sheet.setBaseAbilityScores(15, 14, 13, 12, 10, 8);
		sheet.setExperience(Integer.MAX_VALUE);
		sheet.setRace(race);
		
		sheet.refreshSheet();
		
		System.out.println(TestUtil.toString(sheet));
		
		System.out.println("-=-=- Missing -=-=-");
		lang.missing().forEach(System.out::println);
	}
}
