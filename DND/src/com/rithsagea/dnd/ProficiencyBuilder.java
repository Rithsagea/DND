package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Proficiency;
import com.rithsagea.dnd.api5e.Datapack;

public class ProficiencyBuilder {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		for(Proficiency prof : data5e.Proficiency.values()) {
			book.register(prof);
		}
		
		SourceRegistry.saveBooks();
	}
}
