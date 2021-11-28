package com.rithsagea.dnd;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
import com.rithsagea.dnd.api.types.extras.SpellcastingSlots;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eClass;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eClassLevel;
import com.rithsagea.dnd.api5e.data.extra.EquipmentOption;
import com.rithsagea.dnd.api5e.data.extra.EquipmentStack;
import com.rithsagea.dnd.api5e.data.extra.ProficiencyOptions;
import com.rithsagea.dnd.base.MysticArcanumInfo;
import com.rithsagea.dnd.base.SpellSlotCosts;

public class ClassBuilder {
	
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
	
	private static SpellcastingSlots toSpellcasting(List<Integer> list) {
		SpellcastingSlots spellcasting = new SpellcastingSlots();
		
		spellcasting.spellsKnown = list.get(0);
		spellcasting.cantripsKnown = list.get(10);
		spellcasting.spellSlotsLevel1 = list.get(1);
		spellcasting.spellSlotsLevel2 = list.get(2);
		spellcasting.spellSlotsLevel3 = list.get(3);
		spellcasting.spellSlotsLevel4 = list.get(4);
		spellcasting.spellSlotsLevel5 = list.get(5);
		spellcasting.spellSlotsLevel6 = list.get(6);
		spellcasting.spellSlotsLevel7 = list.get(7);
		spellcasting.spellSlotsLevel8 = list.get(8);
		spellcasting.spellSlotsLevel9 = list.get(9);
		
		return spellcasting;
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
		
		res.equipment = new ArrayList<>();
		for(EquipmentStack eq : model.startingEquipment) {
			ItemStack stack = new ItemStack();
			stack.count = eq.count;
			stack.item = eq.equipment;
			
			res.equipment.add(stack);
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
				lvl.classId = res.id;
				lvl.level = mlvl.level;
				lvl.abilityScoreBonus = mlvl.abilityScoreBonus;
				lvl.proficiencyBonus = mlvl.proficiencyBonus;
				lvl.features = mlvl.features;
				
				res.levels.set(x, lvl);
			}
		}
		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createBarbarian() {
		Dnd5eClass model = data5e.DndClass.get("barbarian");
		DndClass c = createClass(model);
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(0).options.get(1)).type = "category";
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(0)).value.count = 2;
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).type = "category";
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).extra = new HashMap<>(model.levels.get(x).classSpecific);
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createBard() {
		Dnd5eClass model = data5e.DndClass.get("bard");
		DndClass c = createClass(model);
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(0).options.get(2)).type = "category";
		((OptionItem<ItemStack>)c.equipmentOptions.get(2).options.get(1)).type = "category";
		
		c.spellcasting = model.spellcastingInfo;
		c.spells = model.spells;
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).extra = new HashMap<>();
			
			c.levels.get(x).spellcasting = toSpellcasting(model.levels.get(x).spellcasting);
			c.levels.get(x).extra.put("bardicInspirationDie", "1d" + model.levels.get(x).classSpecific.get("bardic_inspiration_die"));
			c.levels.get(x).extra.put("songOfRestDie", "1d" + model.levels.get(x).classSpecific.get("song_of_rest_die"));
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createCleric() {
		Dnd5eClass model = data5e.DndClass.get("cleric");
		DndClass c = createClass(model);
		
		c.equipmentOptions.get(2).options.add(new OptionList(Arrays.asList(
				new OptionItem<ItemStack>("item", new ItemStack("crossbow-light", 1)),
				new OptionItem<ItemStack>("item", new ItemStack("crossbow-bolt", 20)))));
		((OptionItem<ItemStack>)c.equipmentOptions.get(2).options.get(0)).type = "category";
		c.equipmentOptions.get(4).options.add(new OptionItem<ItemStack>("category", new ItemStack("holy-symbols", 1)));
		
		c.spellcasting = model.spellcastingInfo;
		c.spells = model.spells; // TODO: cleric is weird, and has several domains; implement in features
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).extra = new HashMap<>();
			
			c.levels.get(x).spellcasting = toSpellcasting(model.levels.get(x).spellcasting);
			c.levels.get(x).extra.put("channelDivinityCharges", model.levels.get(x).classSpecific.get("channel_divinity_charges"));
			c.levels.get(x).extra.put("destroyUndeadChallengeRating", model.levels.get(x).classSpecific.get("destroy_undead_cr"));
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createDruid() {
		Dnd5eClass model = data5e.DndClass.get("druid");
		DndClass c = createClass(model);
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(0).options.get(1)).type = "category";
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).value.item = "simple-melee-weapons";
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).type = "category"; // TODO: this is simple melee weapons
		c.equipmentOptions.get(2).options.add(new OptionItem<ItemStack>("category", new ItemStack("druidic-foci", 1)));
		
		c.spellcasting = model.spellcastingInfo; //TODO: spells like cleric ahhhh
		c.spells = model.spells;
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).extra = new HashMap<>();
			
			c.levels.get(x).spellcasting = toSpellcasting(model.levels.get(x).spellcasting);
			
			if(x < 2)
				c.levels.get(x).extra.put("wildShapeMaxChallengeRating", 0.25d);
			else if(x < 4) c.levels.get(x).extra.put("wildShapeMaxChallengeRating", 0.5d);
			else c.levels.get(x).extra.put("wildShapeMaxChallengeRating", 1d);
			c.levels.get(x).extra.put("wildShapeSwim", model.levels.get(x).classSpecific.get("wild_shape_swim") == 1);
			c.levels.get(x).extra.put("wildShapeFly", model.levels.get(x).classSpecific.get("wild_shape_fly") == 1);
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createFighter() {
		Dnd5eClass model = data5e.DndClass.get("fighter");
		DndClass c = createClass(model);
		
		c.equipmentOptions.get(0).options.add(new OptionList(Arrays.asList(
				new OptionItem<ItemStack>("item", new ItemStack("leather-armor", 1)),
				new OptionItem<ItemStack>("item", new ItemStack("longbow", 1)),
				new OptionItem<ItemStack>("item", new ItemStack("arrow", 20)))));
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(0)).type = "category";
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(0)).value.count = 2;
		c.equipmentOptions.get(1).options.add(0, new OptionList(Arrays.asList(
				new OptionItem<ItemStack>("item", new ItemStack("shield", 1)),
				new OptionItem<ItemStack>("category", new ItemStack("martial-weapons", 1)))));
		
		c.equipmentOptions.get(2).options.add(new OptionList(Arrays.asList(
				new OptionItem<ItemStack>("item", new ItemStack("crossbow-light", 1)),
				new OptionItem<ItemStack>("item", new ItemStack("crossbow-bolt", 20)))));
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).extra = new HashMap<>();
			
			c.levels.get(x).extra.put("actionSurges", model.levels.get(x).classSpecific.get("action_surges"));
			c.levels.get(x).extra.put("indomitableUses", model.levels.get(x).classSpecific.get("indomitable_uses"));
			c.levels.get(x).extra.put("extraAttacks", model.levels.get(x).classSpecific.get("extra_attacks"));
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createMonk() {
		Dnd5eClass model = data5e.DndClass.get("monk");
		DndClass c = createClass(model);
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(0).options.get(1)).type = "category";
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).extra = new HashMap<>();
			c.levels.get(x).extra.put("martialArts", String.format("%dd%d", 
					model.levels.get(x).classSpecific.get("dice_count"),
					model.levels.get(x).classSpecific.get("dice_value")));
			c.levels.get(x).extra.put("kiPoints", model.levels.get(x).classSpecific.get("ki_points"));
			c.levels.get(x).extra.put("unarmoredMovement", model.levels.get(x).classSpecific.get("unarmored_movement"));
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createPaladin() {
		Dnd5eClass model = data5e.DndClass.get("paladin");
		DndClass c = createClass(model);
		
		c.equipmentOptions.get(0).options.set(0, new OptionList(Arrays.asList(
				new OptionItem<ItemStack>("category", new ItemStack("martial-weapons", 1)),
				new OptionItem<ItemStack>("item", new ItemStack("shield", 1)))));
		c.equipmentOptions.get(0).options.add(new OptionItem<ItemStack>("category", new ItemStack("martial-weapons", 2)));
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(0)).value.count = 5;
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).type = "category";
		c.equipmentOptions.get(3).options.add(new OptionItem<ItemStack>("category", new ItemStack("holy-symbols", 1)));
		
		c.spellcasting = model.spellcastingInfo;
		c.spells = model.spells;
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).spellcasting = toSpellcasting(model.levels.get(x).spellcasting);
			c.levels.get(x).extra = new HashMap<>();
			
			c.levels.get(x).extra.put("auraRange", model.levels.get(x).classSpecific.get("aura_range"));
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createRanger() {
		Dnd5eClass model = data5e.DndClass.get("ranger");
		DndClass c = createClass(model);
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(0)).value.count = 2;
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).type = "category";
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).value.count = 2;
		
		c.spellcasting = model.spellcastingInfo;
		c.spells = model.spells;
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).spellcasting = toSpellcasting(model.levels.get(x).spellcasting);
			
			c.levels.get(x).extra = new HashMap<>();
			
			c.levels.get(x).extra.put("favoredEnemies", model.levels.get(x).classSpecific.get("favored_enemies"));
			c.levels.get(x).extra.put("favoredTerrain", model.levels.get(x).classSpecific.get("favored_terrain"));
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
			c.levels.get(x).extra.put("sneakAttack", String.format("%dd%d",
					sneakAttackData.get("dice_count"),
					sneakAttackData.get("dice_value")));
		}
		
		return c;
	}
	
	private static int getFromMap(Map<String, Integer> map, String key) {
		if(map.containsKey(key)) return map.get(key);
		return -1;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createSorcerer() {
		Dnd5eClass model = data5e.DndClass.get("sorcerer");
		DndClass c = createClass(model);
		
		c.equipmentOptions.get(0).options.add(0, new OptionList(Arrays.asList(
				new OptionItem<ItemStack>("item", new ItemStack("crossbow-light", 1)),
				new OptionItem<ItemStack>("item", new ItemStack("crossbow-bolt", 20)))));
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).type = "category";
		
		c.spellcasting = model.spellcastingInfo;
		c.spells = model.spells;
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).spellcasting = toSpellcasting(model.levels.get(x).spellcasting);
			
			c.levels.get(x).extra = new HashMap<>();
			
			c.levels.get(x).extra.put("sorceryPoints", model.levels.get(x).classSpecific.get("sorcery_points"));
			c.levels.get(x).extra.put("metamagicKnown", model.levels.get(x).classSpecific.get("metamagic_known"));
			SpellSlotCosts costs = new SpellSlotCosts();
			costs.spellSlotLevel1Cost = getFromMap(model.levels.get(x).classSpecific, "spell_slot_level_1");
			costs.spellSlotLevel2Cost = getFromMap(model.levels.get(x).classSpecific, "spell_slot_level_2");
			costs.spellSlotLevel3Cost = getFromMap(model.levels.get(x).classSpecific, "spell_slot_level_3");
			costs.spellSlotLevel4Cost = getFromMap(model.levels.get(x).classSpecific, "spell_slot_level_4");
			costs.spellSlotLevel5Cost = getFromMap(model.levels.get(x).classSpecific, "spell_slot_level_5");
			
			c.levels.get(x).extra.put("spellSlotCosts", costs);
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createWarlock() {
		Dnd5eClass model = data5e.DndClass.get("warlock");
		DndClass c = createClass(model);
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(0).options.get(0)).type = "category";
		c.equipmentOptions.get(0).options.add(0, new OptionList(Arrays.asList(
				new OptionItem<ItemStack>("item", new ItemStack("crossbow-light", 1)),
				new OptionItem<ItemStack>("item", new ItemStack("crossbow-bolt", 20)))));
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).type = "category";
		c.equipmentOptions.get(3).options.add(new OptionItem<ItemStack>("category", new ItemStack("simple-weapons", 1)));
		
		c.spellcasting = model.spellcastingInfo;
		c.spells = model.spells;
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).spellcasting = toSpellcasting(model.levels.get(x).spellcasting);
			c.levels.get(x).extra = new HashMap<>();
			c.levels.get(x).extra.put("invocationsKnown", model.levels.get(x).classSpecific.get("invocations_known"));
			
			MysticArcanumInfo arcana = new MysticArcanumInfo();
			arcana.mysticArcanumLevel6 = getFromMap(model.levels.get(x).classSpecific, "mystic_arcanum_level_6");
			arcana.mysticArcanumLevel7 = getFromMap(model.levels.get(x).classSpecific, "mystic_arcanum_level_7");
			arcana.mysticArcanumLevel8 = getFromMap(model.levels.get(x).classSpecific, "mystic_arcanum_level_8");
			arcana.mysticArcanumLevel9 = getFromMap(model.levels.get(x).classSpecific, "mystic_arcanum_level_9");
			c.levels.get(x).extra.put("mystic_arcana", arcana);
		}
		
		return c;
	}
	
	@SuppressWarnings("unchecked")
	private static DndClass createWizard() {
		Dnd5eClass model = data5e.DndClass.get("wizard");
		DndClass c = createClass(model);
		
		((OptionItem<ItemStack>)c.equipmentOptions.get(1).options.get(1)).type = "category";
		((OptionItem<ItemStack>)c.equipmentOptions.get(2).options.get(1)).value.item = "explorers-pack";
		
		c.spellcasting = model.spellcastingInfo;
		c.spells = model.spells;
		
		for(int x = 0; x < 20; x++) {
			c.levels.get(x).spellcasting = toSpellcasting(model.levels.get(x).spellcasting);
			c.levels.get(x).extra = new HashMap<>();
			c.levels.get(x).extra.put("arcaneRecoveryLevels", model.levels.get(x).classSpecific.get("arcane_recovery_levels"));
		}
		
		return c;
	}
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		System.out.println(data5e.DndClass.keySet().stream().sorted().collect(Collectors.toList()));
		SourceRegistry.getItem("monk", DndClass.class);
		
		book.register("barbarian", createBarbarian());
		book.register("bard", createBard());
		book.register("cleric", createCleric());
		book.register("druid", createDruid());
		book.register("fighter", createFighter());
		book.register("monk", createMonk());
		book.register("paladin", createPaladin());
		book.register("ranger", createRanger());
		book.register("rogue", createRogue());
		book.register("sorcerer", createSorcerer());
		book.register("warlock", createWarlock());
		book.register("wizard", createWizard());
		
		SourceRegistry.saveBooks();
	}
}
