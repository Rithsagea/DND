package test.rithsagea.dnd;

import java.util.ArrayList;
import java.util.List;

import com.rithsagea.dnd.classes.artificer.ArtificerClass;
import com.rithsagea.util.choice.Choice;
import com.rithsagea.util.choice.Option;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.LanguageManager;
import api.rithsagea.dnd.types.classes.Feature;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Equipment;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.util.choice.OptionedItem;

public class CharacterCreationTest {
	
	private static String format(int num) {
		if(num > 0) return "+" + num;
		return "" + num;
	}
	
	public static void main(String[] args) {
		LanguageManager lang = LanguageManager.getInstance();
		
		CharacterSheet sheet = new CharacterSheet();
		sheet.setBaseAbilityScores(8, 15, 10, 15, 10, 13);
		sheet.setName("Sherukin Silverwing");
		sheet.setPlayerName("Alfiend");
		sheet.setCharacterClass(new ArtificerClass());
		sheet.refresh();
		
		//choices
		System.out.println("-=-=- Options -=-=-");
		List<Feature> features = new ArrayList<>(sheet.getFeatures());
		List<List<Integer>> choiceValues = List.of(
				List.of(0),
				List.of(0, 6));
		int optionIndex = 0;
		
		for(Feature feature : features) {
			if(feature instanceof OptionedItem) {
				OptionedItem optionedItem = (OptionedItem) feature;
				for(Option op : optionedItem.getOptions()) {
					System.out.printf("-- %d -- Option[%d]\n", optionIndex, op.getCount());
					List<Choice> choices = new ArrayList<>(op.getChoices());
					for(int j = 0; j < choices.size(); j++) {
						System.out.println(j + ": " + choices.get(j));
					}
					
					for(int j = 0; j < op.getCount(); j++) {
						op.choose(choices.get(choiceValues.get(optionIndex).get(j)));
					}
					
					optionIndex++;
				}
			}
		}
		
		sheet.setHitPoints(Integer.MAX_VALUE); // Full Health
		sheet.refresh();
		
		System.out.println("-=-=- Overview -=-=-");
		System.out.printf("Character: %s\n", sheet.getCharacterName());
		System.out.printf("Player: %s\n", sheet.getPlayerName());
		//race
		System.out.printf("Class: %s\n", sheet.getCharacterClass());
		//multiclassing
		
		System.out.printf("Experience: %d\n", sheet.getExperience());
		System.out.printf("Level: %d\n", sheet.getLevel());
		System.out.printf("Proficiency Bonus: %s\n", format(sheet.getProficiencyBonus()));
		System.out.printf("Hit Points: (%d/%d)\n", sheet.getHitPoints(), sheet.getMaxHitPoints());
		System.out.printf("Hit Dice: %s\n", sheet.getHitDice());
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
			System.out.printf("%s: %s%s\n",
					ability, format(sheet.getSavingThrow(ability)),
					sheet.hasSavingProficiency(ability) ? "*" : "");
		}
		
		System.out.println("-=-=- Skills -=-=-");
		for(Skill skill : Skill.values()) {
			System.out.printf("%15.15s: %s%s\n",
					skill, format(sheet.getSkillModifier(skill)),
					sheet.hasSkillProficiency(skill) ? "*" : "");
		}
		
		System.out.println("-=-=- Proficiencies -=-=-");
		for(Equipment equipment : sheet.getEquipmentProficiencies()) {
			System.out.printf("%s\n", equipment);
		}
		
		System.out.println("-=-=- Traits & Features -=-=-");
		for(Feature feature : sheet.getFeatures()) {
			System.out.printf("%s\n", feature.getName());
		}
		//TODO
		
		System.out.println("-=-=- Missing -=-=-");
		lang.missing().forEach(System.out::println);
	}
}
