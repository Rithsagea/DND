package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.CharacterSheet;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Alignment;

public class SkeletonRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	public static void main(String[] args) {
		SourceRegistry reg = new SourceRegistry(SOURCE_DIRECTORY);
		reg.init();
		
		CharacterSheet c = new CharacterSheet();
		
		c.name = "Varikane";
		c.playerName = "Rithsagea Aquadom";
		c.alignment = reg.getItem("true-neutral", Alignment.class);
	}
}
