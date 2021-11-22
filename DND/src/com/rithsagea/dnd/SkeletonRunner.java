package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.CharacterSheet;
import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Skill;
import com.rithsagea.dnd.api5e.Datapack;

public class SkeletonRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		Datapack data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		for(Skill item : data5e.Skill.values()) {
			book.register(item.id, item);
		}
		SourceRegistry.saveBooks();
		
		CharacterSheet c = new CharacterSheet();
		
		c.name = "Varikane";
		c.playerName = "Rithsagea Aquadom";
		c.alignment = "neutral";
		
		c.experiencePoints = 500;
		
		c.abilityScores.put("str", 8);
		c.abilityScores.put("dex", 14);
		c.abilityScores.put("con", 10);
		c.abilityScores.put("int", 15);
		c.abilityScores.put("wis", 14);
		c.abilityScores.put("cha", 14);
		
		c.calc();
		
		System.out.println("Level: " + c.level);
		System.out.println(c.proficiencyBonus);
//		System.out.println(SourceRegistry.getKeys(Skill.class));
	}
}
