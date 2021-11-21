package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.CharacterSheet;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Alignment;

public class SkeletonRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		CharacterSheet c = new CharacterSheet();
		
		c.name = "Varikane";
		c.playerName = "Rithsagea Aquadom";
		c.alignment = SourceRegistry.getItem("true-neutral", Alignment.class);
	}
}
