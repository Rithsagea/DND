package com.rithsagea.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
	
	private static DndSubclassLevel createLevel(String id, int lvl, Collection<String> features) {
		DndSubclassLevel level = new DndSubclassLevel();
		
		level.id = id + "-" + lvl;
		level.subclassId = id;
		level.level = lvl;
		level.features = new ArrayList<>(features);
		
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
	
	private static DndSubclass createTotemWarrior() {
		//TODO add features here
		DndSubclass subclass = new DndSubclass();
		subclass.id = "totem-warrior";
		subclass.name = "Totem Warrior";
		subclass.flavor = "Primal Path";
		subclass.description = "The Path  of the Totem Warrior is a spiritual journey, as the barbarian accepts a spirit animal  as guide, protector, and inspiration. In battle, your totem spirit fills you with supernatural might, adding magical fuel to your barbarian rage. Most barbarian tribes consider a totem animal to be kin to a particular clan. In such cases, it is unusual for an individual to have more than one totem animal spirit, though exceptions exist.";
		subclass.parentClass = "barbarian";
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		DndSubclassLevel level;
		
		level = createLevel("totem-warrior", 3, Arrays.asList("spirit-seeker", "totem-spirit"));
		subclass.levels.set(2, level);
		
		level = createLevel("totem-warrior", 6, Arrays.asList("aspect-of-the-beast"));
		subclass.levels.set(5, level);
		
		level = createLevel("totem-warrior", 10, Arrays.asList("spirit-walker"));
		subclass.levels.set(9, level);
		
		level = createLevel("totem-warrior", 14, Arrays.asList("spirit-walker"));
		subclass.levels.set(13, level);
		
		return subclass;
	}
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		System.out.println(data5e.DndSubclass.keySet().stream().sorted().collect(Collectors.toList()));
		
		book.register("berserker", createBerserker());
		book.register("totem-warrior", createTotemWarrior());
		SourceRegistry.saveBooks();
	}
}
