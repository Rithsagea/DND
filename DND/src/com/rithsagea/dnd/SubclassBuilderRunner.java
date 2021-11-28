package com.rithsagea.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.DndSubclass;
import com.rithsagea.dnd.api.types.DndSubclassLevel;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eSubclass;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eSubclassLevel;

public class SubclassBuilderRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	private static DndSubclass createSubclass(Dnd5eSubclass model) {
		DndSubclass subclass = new DndSubclass();
		
		subclass.id = model.id;
		subclass.name = model.name;
		subclass.flavor = model.flavor;
		subclass.description = model.description;
		subclass.parentClass = model.parentClass;
		
		subclass.levels = new ArrayList<>();
		subclass.levels.addAll(Collections.nCopies(20, null));
		
		return subclass;
	}
	
	private static DndSubclassLevel createLevel(Dnd5eSubclassLevel model) {
		DndSubclassLevel level = new DndSubclassLevel();
		level.id = model.id;
		level.features = model.features;
		level.level = model.level;
		return level;
	}
	
	private static DndSubclass createBerserker() {
		Dnd5eSubclass model = data5e.DndSubclass.get("berserker");
		DndSubclass subclass = createSubclass(model);
		
		for(int x = 0; x < 20; x++) {
			if(model.levels.get(x) != null) {
				subclass.levels.set(x, createLevel(model.levels.get(x)));
				subclass.levels.get(x).subclassId = subclass.id;
			}
		}
		
		return subclass;
	}
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		System.out.println(data5e.DndSubclass.keySet().stream().sorted().collect(Collectors.toList()));
		
		book.register("berserker", createBerserker());
		SourceRegistry.saveBooks();
	}
}
