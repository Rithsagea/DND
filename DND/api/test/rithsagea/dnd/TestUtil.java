package test.rithsagea.dnd;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import api.rithsagea.dnd.character.CharacterSheet;
import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.types.traits.Trait;

public class TestUtil {
	
	private static List<String> serializeObject(Object obj) {
		List<String> res = new ArrayList<>();
		
		for(Field field : obj.getClass().getDeclaredFields()) {
			if(Modifier.isStatic(field.getModifiers())) continue; //Skip Static Fields
			field.setAccessible(true);
			try {
				Object val = field.get(obj);
				if(val instanceof Map) {
					res.add(field.getName() + ":");
					
					for(String line : serializeMap((Map<?, ?>) val)) {
						res.add("\t" + line);
					}
					
				} else if (val instanceof Collection) {
					res.add(field.getName() + ": ");
					for(String line : serializeCollection((Collection<?>) val)) {
						res.add("\t" + line);
					}
				} else {
					res.add(field.getName() + ": " + val);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	private static List<String> serializeMap(Map<?, ?> map) {
		List<String> res = new ArrayList<>();
		for(Entry<?, ?> entry : map.entrySet()) {
			res.add(entry.getKey() + ": " + entry.getValue());
		}
		
		return res;
	}
	
	private static List<String> serializeCollection(Collection<?> col) {
		List<String> res = new ArrayList<>();
		for(Object obj : col) res.add(obj.toString());
		
		return res;
	}
	
	/**
	 * Reflectively gets details from this object
	 * (is technically nsfw lmao, never again)
	 * @param obj the object to inspect
	 * @return the object's details
	 */
	public static String toString(Object obj) {
		StringBuilder builder = new StringBuilder();
		
		String prefix = "";
		for(String line : serializeObject(obj)) {
			builder.append(prefix);
			builder.append(line);
			prefix = "\n";
		}
		
		return builder.toString();
	}
	
	public static Object getField(Object obj, String name) {
		Field field;
		try {
			field = obj.getClass().getDeclaredField(name);
			field.setAccessible(true);
			return field.get(obj);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String format(int num) {
		return (num <= 0 ? "" : "+") + num;
	}
	
	public static String sheetToString(CharacterSheet sheet) {
		StringBuilder b = new StringBuilder();
		b.append("-=-=- Overview -=-=-\n");
		b.append("Character Name: " + sheet.getName() + "\n");
		b.append("Class: " + null + "\n");
		b.append("Background: " + sheet.getBackground() + "\n");
		b.append("Player Name: " + sheet.getPlayerName() + "\n");
		b.append("Race: " + sheet.getRace() + "\n");
		b.append("Alignment: " + sheet.getAlignment() + "\n");
		b.append("Experience Points: " + sheet.getExperience() + "\n");
		b.append("Level: " + sheet.getLevel() + "\n");
		
		b.append("\n-=-=- Scores -=-=-\n");
		for(Ability a : Ability.values()) {
			b.append(a + ": " + sheet.getAbilityScore(a) + " (" + format(sheet.getAbilityModifier(a)) +")\n");
		}
		
		b.append("\n-=-=- Modifiers -=-=-\n");
		for(Ability a : Ability.values()) {
			b.append(a + ": " + format(sheet.getSavingThrow(a)) + "\n");
		}
		b.append("\n");
		for(Skill s : Skill.values()) {
			b.append(s + ": " + format(sheet.getSkillModifier(s)) + "\n");
		}
		
		b.append("\n-=-=- Misc -=-=-\n");
		b.append("Inspiration: " + sheet.getInspiration() + "\n");
		b.append("Proficiency Bonus: " + format(sheet.getProficiencyBonus()) + "\n");
		b.append("Passive Wisdom: " + sheet.getPassiveWisdom() + "\n");
		b.append("\n");
		b.append("Armor Class: " + null + "\n");
		b.append("Initiative: " + format(sheet.getInitiative()) + "\n");
		b.append("Speed: " + sheet.getSpeed() + "\n");
		b.append("\n");
		b.append("Hit Points: " + null + "/" + null + "\n");
		b.append("Temporary Hit Points: " + null + "\n");
		b.append("Hit Dice: " + null + "\n");
		b.append("\n");
		b.append("Death Saves: " + null + "/" + null + "\n");
		
		b.append("\n-=-=- Traits -=-=-\n");
		for(Trait trait : sheet.getTraits()) {
			b.append(trait + "\n");
		}
		
		return b.toString();
	}
}
