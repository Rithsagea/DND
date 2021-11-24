package com.rithsagea.dnd;

import java.io.File;
import java.util.ArrayList;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.DndClass;
import com.rithsagea.dnd.api.types.extras.OptionItem;
import com.rithsagea.dnd.api.types.extras.Options;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eClass;
import com.rithsagea.dnd.api5e.data.extra.ProficiencyOptions;

public class BuilderRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		Datapack data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		System.out.println(data5e.DndClass.keySet());
		String id = "rogue";
		Dnd5eClass model = data5e.DndClass.get(id);
		DndClass item = SourceRegistry.getItem(id, DndClass.class);
		
		item = new DndClass();
		
		item.id = model.id;
		item.hitDie = model.hitDie;
		item.proficiencies = model.proficiencies;
		item.savingThrows = model.savingThrows;
		
		item.proficiencyOptions = new ArrayList<>();
		for(ProficiencyOptions op : model.proficiencyOptions) {
			Options options = new Options();
			options.count = op.count;
			options.option = new ArrayList<>();
			
			for(String prof : op.proficiencies) {
				OptionItem<String> choice = new OptionItem<>();
				choice.type = "proficiency";
				choice.value = prof;
				options.option.add(choice);
			}
			
			item.proficiencyOptions.add(options);
		}
		
		book.register(id, item);
//		for(Language item : data5e.Language.values()) {
//			book.register(item.id, item);
//		}
		
		SourceRegistry.saveBooks();
	}
}
