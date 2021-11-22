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
		
//		Datapack data5e = Datapack.loadDatapack(new File("5e.json"));
//		SourceBook book = SourceRegistry.getBooks().get("5e");
//		for(Alignment alignment : data5e.Alignment.values()) {
//			book.register(alignment.id, alignment);
//		}
		SourceRegistry.saveBooks();
		
		CharacterSheet c = new CharacterSheet();
		
		c.name = "Varikane";
		c.playerName = "Rithsagea Aquadom";
		c.alignment = SourceRegistry.getItem("neutral", Alignment.class);
		
		System.out.println("asdf");
	}
}
