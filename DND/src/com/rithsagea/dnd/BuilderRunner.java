package com.rithsagea.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.DndClass;
import com.rithsagea.dnd.api.types.DndClassLevel;
import com.rithsagea.dnd.api.types.extras.AbilityScoreRequirement;
import com.rithsagea.dnd.api.types.extras.ItemStack;
import com.rithsagea.dnd.api.types.extras.MulticlassingInfo;
import com.rithsagea.dnd.api.types.extras.OptionItem;
import com.rithsagea.dnd.api.types.extras.OptionList;
import com.rithsagea.dnd.api.types.extras.Options;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eClass;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eClassLevel;
import com.rithsagea.dnd.api5e.data.extra.EquipmentOption;
import com.rithsagea.dnd.api5e.data.extra.EquipmentStack;
import com.rithsagea.dnd.api5e.data.extra.ProficiencyOptions;

public class BuilderRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	private static Options toOptions(ProficiencyOptions op) {
		Options options = new Options();
		options.count = op.count;
		options.options = new ArrayList<>();
		
		for(String prof : op.proficiencies) {
			OptionItem<String> choice = new OptionItem<>();
			choice.type = "proficiency";
			choice.value = prof;
			options.options.add(choice);
		}
		
		return options;
	}
	
	private static Options toOptions(EquipmentOption op) {
		Options options = new Options();
		options.count = op.count;
		options.options = new ArrayList<>();
		
		for(String eq : op.equipment) {
			OptionItem<ItemStack> choice = new OptionItem<>();
			choice.type = "item";
			choice.value = new ItemStack();
			
			choice.value.item = eq;
			choice.value.count = 1;
			
			options.options.add(choice);
		}
		
		return options;
	}
	
	private static DndClass createClass(Dnd5eClass model) {
		DndClass res = new DndClass();
		
		res.id = model.id;
		res.name = model.name;
		res.hitDie = model.hitDie;
		res.proficiencies = model.proficiencies;
		res.savingThrows = model.savingThrows;
		
		res.proficiencyOptions = new ArrayList<>();
		for(ProficiencyOptions op : model.proficiencyOptions)
			res.proficiencyOptions.add(toOptions(op));
		
		res.startingEquipment = new ArrayList<>();
		for(EquipmentStack eq : model.startingEquipment) {
			ItemStack stack = new ItemStack();
			stack.count = eq.count;
			stack.item = eq.equipment;
			
			res.startingEquipment.add(stack);
		}
		
		res.equipmentOptions = new ArrayList<>();
		for(EquipmentOption op : model.equipmentOptions)
			res.equipmentOptions.add(toOptions(op));
		
		res.subclasses = model.subclasses;
		
		res.multiclassing = new MulticlassingInfo();
		res.multiclassing.abilityScoreRequirements = new Options();
		res.multiclassing.abilityScoreRequirements.count = model.abilityScoreRequirementCount;
		res.multiclassing.abilityScoreRequirements.options = new ArrayList<>();
		for(Entry<String, Integer> req : model.abilityScoreRequirement.entrySet())
			res.multiclassing.abilityScoreRequirements.options.add(new OptionItem<AbilityScoreRequirement>(
					"ability_score", new AbilityScoreRequirement(req.getKey(), req.getValue())));
		res.multiclassing.proficiencies = model.multiclassingProficiencies;
		res.multiclassing.proficiencyOptions = new ArrayList<>();
		for(ProficiencyOptions op : model.proficiencyOptions)
			res.multiclassing.proficiencyOptions.add(toOptions(op));
		
		res.levels = new ArrayList<>();
		res.levels.addAll(Collections.nCopies(20, null));
		for(int x = 0; x < model.levels.size(); x++) {
			if(model.levels.get(x) != null) {
				Dnd5eClassLevel mlvl = model.levels.get(x);
				DndClassLevel lvl = new DndClassLevel();
				
				lvl.id = mlvl.id;
				lvl.className = res.id;
				lvl.level = mlvl.level;
				lvl.abilityScoreBonus = mlvl.abilityScoreBonus;
				lvl.proficiencyBonus = mlvl.proficiencyBonus;
				lvl.features = mlvl.features;
				
				res.levels.set(x, lvl);
			}
		}
		
		//TODO magic. add to "extras" on a per class basis
		//TODO magic for levels
		return res;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createBarbarian() {
		Dnd5eClass model = data5e.DndClass.get("barbarian");
		DndClass c = createClass(model);
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(0)).value.count = 2;
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).extra = new HashMap<>(model.levels.get(x).classSpecific);
		}
		
		return c;
	}
	
	private static DndClass createRogue() {
		Dnd5eClass model = data5e.DndClass.get("rogue");
		DndClass c = createClass(model);
		c.equipmentOptions.get(1).options.add(new OptionList(Arrays.asList(
				new OptionItem<ItemStack>("item", new ItemStack("shortbow", 1)),
				new OptionItem<ItemStack>("item", new ItemStack("arrow", 20)))));
		for(int x = 0; x < 20; x++) {
			Map<String, Integer> sneakAttackData = model.levels.get(x).classSpecific;
			c.levels.get(x).extra = new HashMap<>();
			c.levels.get(x).extra.put("sneak_attack", String.format("%dd%d",
					sneakAttackData.get("dice_count"),
					sneakAttackData.get("dice_value")));
		}
		
		return c;
	}
	
	private static DndClass createCleric() {
		Dnd5eClass model = data5e.DndClass.get("cleric");
		DndClass c = createClass(model);
		
		return c;
	}
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		System.out.println(data5e.DndClass.keySet());
		SourceRegistry.getItem("barbarian", DndClass.class);
		
		book.register("barbarian", createBarbarian());
		book.register("rogue", createRogue());
		book.register("cleric", createCleric());
		
		SourceRegistry.saveBooks();
	}
}
