package legacy.rithsagea.dnd;

import java.io.File;

import legacy.rithsagea.dnd.api.CharacterSheetTemplate;
import legacy.rithsagea.dnd.api.SourceRegistry;
import legacy.rithsagea.dnd.api.types.Coin;

public class SkeletonRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	private static SourceRegistry registry = SourceRegistry.getInstance();
	
	public static void main(String[] args) {
		registry.init(SOURCE_DIRECTORY);
		registry.load();
		CharacterSheetTemplate c = new CharacterSheetTemplate();
		
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
		
		c.money.put("cp", 50);
		c.money.put("sp", 20);
		c.money.put("ep", 0);
		c.money.put("gp", 50);
		c.money.put("pp", 0);
		
		c.languages.add("common");
		
		c.characterClass = "wizard";
		
		c.calc();
		
		System.out.println("Level: " + c.level);
		System.out.println(c.proficiencyBonus);
		System.out.println(c.spellcastingAbility);
		
		for(Coin coin : registry.getItems(Coin.class)) {
			System.out.println(coin.name + ": " + c.money.get(coin.id) + coin.id);
		}
	}
}