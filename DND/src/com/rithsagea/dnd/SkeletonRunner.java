package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.CharacterSheet;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Coin;

public class SkeletonRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
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
		
		c.money.put("cp", 50);
		c.money.put("sp", 20);
		c.money.put("ep", 0);
		c.money.put("gp", 50);
		c.money.put("pp", 0);
		
		c.languages.add("common");
		
		System.out.println("Level: " + c.level);
		System.out.println(c.proficiencyBonus);
		
		for(Coin coin : SourceRegistry.getItems(Coin.class)) {
			System.out.println(coin.name + ": " + c.money.get(coin.id) + coin.id);
		}
//		System.out.println(SourceRegistry.getKeys(Skill.class));
	}
}
