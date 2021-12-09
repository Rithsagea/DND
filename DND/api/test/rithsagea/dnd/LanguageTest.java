package test.rithsagea.dnd;

import api.rithsagea.dnd.types.enums.Ability;
import api.rithsagea.dnd.types.enums.Alignment;
import api.rithsagea.dnd.types.enums.Size;
import api.rithsagea.dnd.types.enums.Skill;
import api.rithsagea.dnd.util.TextManager;
import api.rithsagea.dnd.util.WordUtil;

public class LanguageTest {
	public static void main(String[] args) {
		TextManager text = TextManager.getInstance();
		text.load();
		
		for(Ability ability : Ability.values()) {
			text.setMessage("Ability." + WordUtil.formatId(ability.name()) + ".ShortName", ability.name().substring(0, 3).toUpperCase());
			text.setMessage("Ability." + WordUtil.formatId(ability.name()) + ".Name", WordUtil.toTitleCase(ability.name()));
			
			System.out.println(ability + ": " + ability.getName());
		}
		
		System.out.println();
		for(Alignment alignment : Alignment.values()) {
			text.setMessage("Alignment." + WordUtil.formatId(alignment.name()) + ".ShortName", WordUtil.initials(alignment.name()));
			text.setMessage("Alignment." + WordUtil.formatId(alignment.name()) + ".Name", WordUtil.toTitleCase(alignment.name()));
			
			System.out.println(alignment + ": " + alignment.getName());
		}
		
		System.out.println();
		for(Skill skill : Skill.values()) {
			text.setMessage("Skill." + WordUtil.formatId(skill.name()) + ".Name", WordUtil.toTitleCase(skill.name()));

			System.out.println(skill);
		}
		
		System.out.println();
		for(Size size : Size.values()) {
			text.setMessage("Size." + WordUtil.formatId(size.name()) + ".Name", WordUtil.toTitleCase(size.name()));

			System.out.println(size);
		}
		
		text.save();
	}
}
