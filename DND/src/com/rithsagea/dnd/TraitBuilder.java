package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Trait;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.races.DndRaceTrait;

public class TraitBuilder {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		for(String key : data5e.DndRaceTrait.keySet()) {
			Trait feature = new Trait();
			DndRaceTrait model = data5e.DndRaceTrait.get(key);
			
			feature.id = model.id;
			feature.name = model.name;
			feature.description = model.description;
			
			book.register(key, feature);
		}
		
		SourceRegistry.saveBooks();
	}
}
