package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Feature;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.classes.DndClassFeature;

public class FeatureBuilder {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		for(String key : data5e.DndSubclass.keySet()) {
			Feature feature = new Feature();
			DndClassFeature model = data5e.DndClassFeature.get(key);
			
			feature.id = model.id;
			feature.classId = model.classId;
			feature.subclassId = model.subclassId;
			feature.description = model.description;
			feature.level = model.level;
			
			book.register(key, feature);
		}
		
		SourceRegistry.saveBooks();
	}
}
