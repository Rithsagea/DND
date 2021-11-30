package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api5e.Datapack;

public class SubraceBuilder {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		for(String key : data5e.DndSubrace.keySet()) {
			
		}
		
		System.out.println(data5e.DndSubrace.keySet());
		SourceRegistry.saveBooks();
	}
}
