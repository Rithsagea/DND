package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Monster;
import com.rithsagea.dnd.api5e.Datapack;

public class MonsterBuilder {
private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		for(Monster m : data5e.Monster.values()) {
			book.register(m);
		}
		
		SourceRegistry.saveBooks();
	}
}
