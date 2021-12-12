package test.rithsagea.dnd;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.enums.Alignment;
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
		
		System.out.println(TestUtil.toString(sheet));
		
		lang.missing();
	}
}
