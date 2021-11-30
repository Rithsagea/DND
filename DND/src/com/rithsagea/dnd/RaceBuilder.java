package com.rithsagea.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.DndRace;
import com.rithsagea.dnd.api.types.extras.OptionItem;
import com.rithsagea.dnd.api.types.extras.Options;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.extra.AbilityBonus;
import com.rithsagea.dnd.api5e.data.races.Dnd5eRace;

public class RaceBuilder {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		for(String key : data5e.DndRace.keySet()) {
			Dnd5eRace model = data5e.DndRace.get(key);
			DndRace race = new DndRace();
			
			race.id = model.id;
			race.age = model.age;
			race.alignment = model.alignment;
			race.size = model.size;
			race.sizeDescription = model.sizeDescription;
			
			race.speed = model.speed;
			
			race.abilityBonuses = new HashMap<>();
			for(AbilityBonus bonus : model.bonuses) {
				race.abilityBonuses.put(bonus.ability, bonus.bonus);
			}
			
			race.proficiencies = model.proficiencies;
			if(race.proficiencies.isEmpty()) race.proficiencies = null;
			race.proficiencyOptions = new Options();
			race.proficiencyOptions.count = model.proficiencyOptions.count;
			race.proficiencyOptions.options = new ArrayList<>();
			for(String prof : model.proficiencyOptions.proficiencies) {
				race.proficiencyOptions.options.add(new OptionItem<String>("proficiency", prof));
			}
			if(race.proficiencyOptions.count == 0) race.proficiencyOptions = null;
			
			race.languages = model.languages;
			race.languageDesc = model.languageDesc;
			race.traits = model.traits;
			
			book.register(race);
		}
		
		System.out.println(data5e.DndRace.keySet());
		SourceRegistry.saveBooks();
	}
}
