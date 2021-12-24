package test.rithsagea.dnd;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.util.LanguageManager;

public class CharacterCreationTest {
	
	private static String format(int num) {
		if(num > 0) return "+" + num;
		return "" + num;
	}
	
	public static void main(String[] args) {
		LanguageManager lang = LanguageManager.getInstance();
//		Registry registry = Registry.getInstance();
//		TraitTest.registerTraits(registry);
//		RaceTest.registerRaces(registry);
		
		CharacterSheet sheet = new CharacterSheet();
		sheet.setBaseAbilityScores(8, 15, 10, 15, 10, 13);
		sheet.setName("Sherukin Silverwing");
		sheet.setPlayerName("Alfiend");
		
		sheet.refresh();
		
		System.out.println("-=-=- Overview -=-=-");
		System.out.printf("Character: %s\n", sheet.getCharacterName());
		System.out.printf("Player: %s\n", sheet.getPlayerName());
		//race
		//classes
		System.out.printf("Experience: %d\n", sheet.getExperience());
		System.out.printf("Level: %d\n", sheet.getLevel());
		System.out.printf("Proficiency Bonus: %s\n", format(sheet.getProficiencyBonus()));
		System.out.println();
		System.out.printf("Passive Wisdom: %d\n", sheet.getPassiveWisdom());
		System.out.printf("Initiative: %s\n", format(sheet.getInitiative()));
		System.out.printf("Armor Class: %d\n", sheet.getArmorClass());
		System.out.printf("Speed: %dft\n", sheet.getSpeed());
		
		System.out.println("-=-=- Ability Scores -=-=-");
		for(Ability ability : Ability.values()) {
			System.out.printf("%s: %d (%s)\n",
					ability, sheet.getAbilityScore(ability),
					format(sheet.getAbilityModifier(ability)));
		}
		
		System.out.println("-=-=- Saving Throws -=-=-");
		for(Ability ability : Ability.values()) {
			System.out.printf("%s: %s\n",
					ability, format(sheet.getSavingThrow(ability)));
		}
		
		System.out.println("-=-=- Skills -=-=-");
		for(Skill skill : Skill.values()) {
			System.out.printf("%15.15s: %s\n",
					skill, format(sheet.getSkillModifier(skill)));
		}
		
		System.out.println("-=-=- Missing -=-=-");
		lang.missing().forEach(System.out::println);
	}
}
