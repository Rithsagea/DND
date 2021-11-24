package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.classes.Dnd5eClass;
import com.rithsagea.dnd.api5e.data.classes.DndClassLevel;
import com.rithsagea.dnd.api5e.data.extra.EquipmentOption;
import com.rithsagea.dnd.api5e.data.extra.EquipmentStack;
import com.rithsagea.dnd.api5e.data.extra.ProficiencyOptions;
import com.rithsagea.dnd.api5e.data.extra.SpellcastingInfo;

public class DndClassAdapter implements JsonDeserializer<Dnd5eClass> {

	private Gson gson = new Gson();
	
	@Override
	public Dnd5eClass deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		Dnd5eClass dndClass = new Dnd5eClass();
		JsonObject obj = elem.getAsJsonObject();
		dndClass.id = obj.get("index").getAsString();
		dndClass.name = obj.get("name").getAsString();
		dndClass.hitDie = obj.get("hit_die").getAsInt();
		
		dndClass.proficiencyOptions = new ArrayList<>();
		for(JsonElement e1 : obj.get("proficiency_choices").getAsJsonArray()) {
			ProficiencyOptions options = new ProficiencyOptions();
			dndClass.proficiencyOptions.add(options);
			
			options.count = e1.getAsJsonObject().get("choose").getAsInt();
			options.proficiencies = new ArrayList<>();
			for(JsonElement e2 : e1.getAsJsonObject().get("from").getAsJsonArray()) {
				options.proficiencies.add(e2.getAsJsonObject().get("index").getAsString());
			}
		}
		
		dndClass.proficiencies = new ArrayList<>();
		for(JsonElement e1 : obj.get("proficiencies").getAsJsonArray()) {
			dndClass.proficiencies.add(e1.getAsJsonObject().get("index").getAsString());
		}
		
		dndClass.savingThrows = new ArrayList<>();
		for(JsonElement e1 : obj.get("saving_throws").getAsJsonArray()) {
			dndClass.savingThrows.add(e1.getAsJsonObject().get("index").getAsString());
		}
		
		dndClass.startingEquipment = new ArrayList<>();
		for(JsonElement e1 : obj.get("starting_equipment").getAsJsonArray()) {
			EquipmentStack stack = new EquipmentStack();
			stack.equipment = e1.getAsJsonObject().get("equipment").getAsJsonObject()
					.get("index").getAsString();
			stack.count = e1.getAsJsonObject().get("quantity").getAsInt();
			dndClass.startingEquipment.add(stack);
		}
		
		dndClass.equipmentOptions = new ArrayList<>();
		for(JsonElement e1 : obj.get("starting_equipment_options").getAsJsonArray()) {
			EquipmentOption option = new EquipmentOption();
			dndClass.equipmentOptions.add(option);
			option.count = e1.getAsJsonObject().get("choose").getAsInt();
			
			option.equipment = new ArrayList<>();
			for(JsonElement e2 : e1.getAsJsonObject().get("from").getAsJsonArray()) {
				if(e2.getAsJsonObject().has("equipment")) {
					option.equipment.add(e2.getAsJsonObject().get("equipment").getAsJsonObject()
							.get("index").getAsString());
				}
				if(e2.getAsJsonObject().has("equipment_option")) {
					option.equipment.add(e2.getAsJsonObject().get("equipment_option").getAsJsonObject()
							.get("from").getAsJsonObject().get("equipment_category").getAsJsonObject()
							.get("index").getAsString()); // TODO unpack this
				}
			}
		}
		
		JsonObject multi = obj.get("multi_classing").getAsJsonObject();
		dndClass.multiclassingProficiencies = new ArrayList<>();
		for(JsonElement e1 : multi.get("proficiencies").getAsJsonArray())
			dndClass.multiclassingProficiencies.add(e1.getAsJsonObject().get("index").getAsString());
		
		dndClass.multiclassingProficiencyOptions = new ArrayList<>();
		if(multi.has("proficiency_choices"))
		for(JsonElement e1 : multi.get("proficiency_choices").getAsJsonArray()) {
			ProficiencyOptions option = new ProficiencyOptions();
			dndClass.multiclassingProficiencyOptions.add(option);
			option.count = e1.getAsJsonObject().get("choose").getAsInt();
			option.proficiencies = new ArrayList<>();
			
			for(JsonElement e2 : e1.getAsJsonObject().get("from").getAsJsonArray()) {
				option.proficiencies.add(e2.getAsJsonObject().get("index").getAsString());
			}
		}
		
		dndClass.abilityScoreRequirement = new HashMap<>();
		
		if(multi.has("prerequisite_options")) {
			JsonObject req = multi.get("prerequisite_options").getAsJsonObject();
			dndClass.abilityScoreRequirementCount = req.get("choose").getAsInt();
			for(JsonElement e1 : req.get("from").getAsJsonArray()) {
				dndClass.abilityScoreRequirement.put(e1.getAsJsonObject().get("ability_score")
						.getAsJsonObject().get("index").getAsString(),
						e1.getAsJsonObject().get("minimum_score").getAsInt());
			}
		} else {
			for(JsonElement e1 : multi.get("prerequisites").getAsJsonArray()) {
				dndClass.abilityScoreRequirement.put(e1.getAsJsonObject().get("ability_score")
						.getAsJsonObject().get("index").getAsString(),
						e1.getAsJsonObject().get("minimum_score").getAsInt());
			}
			dndClass.abilityScoreRequirementCount = dndClass.abilityScoreRequirement.size();
		}
		
		dndClass.subclasses = new ArrayList<>();
		for(JsonElement e1 : obj.get("subclasses").getAsJsonArray()) {
			dndClass.subclasses.add(e1.getAsJsonObject().get("index").getAsString());
		}
		
		dndClass.spellcastingInfo = new SpellcastingInfo();
		if(obj.has("spellcasting")) {
			JsonObject spellcasting = obj.get("spellcasting").getAsJsonObject();
			dndClass.spellcastingInfo.level = spellcasting.get("level").getAsInt();
			dndClass.spellcastingInfo.spellcastingAbility = spellcasting.get("spellcasting_ability")
					.getAsJsonObject().get("index").getAsString();
			
			dndClass.spellcastingInfo.info = new HashMap<>();
			for(JsonElement e1 : spellcasting.get("info").getAsJsonArray()) {
				StringBuilder builder = new StringBuilder();
				String prefix = "";
				for(JsonElement e2 : e1.getAsJsonObject().get("desc").getAsJsonArray()) {
					builder.append(prefix);
					builder.append(e2.getAsString());
					prefix = "\n";
				}
				dndClass.spellcastingInfo.info.put(e1.getAsJsonObject().get("name").getAsString(), builder.toString());
			}
		}
		
		dndClass.spells = new ArrayList<>();
		for(JsonElement e1 : gson.fromJson(Dnd5eApiTool.get("/classes/" + dndClass.id + "/spells"), JsonObject.class).get("results").getAsJsonArray())
			dndClass.spells.add(e1.getAsJsonObject().get("index").getAsString());
		
		dndClass.levels = new ArrayList<>(Collections.nCopies(20, null));
		for(JsonElement e1 : gson.fromJson(Dnd5eApiTool.get("/classes/" + dndClass.id + "/levels"), JsonArray.class)) {
			JsonObject lvl = e1.getAsJsonObject();
			if(!lvl.has("subclass")) {
				DndClassLevel classLevel = new DndClassLevel();
				classLevel.level = lvl.get("level").getAsInt();
				classLevel.id = lvl.get("index").getAsString();
				dndClass.levels.set(classLevel.level - 1, classLevel);
				
				classLevel.abilityScoreBonus = lvl.get("ability_score_bonuses").getAsInt();
				classLevel.proficiencyBonus = lvl.get("prof_bonus").getAsInt();
				classLevel.features = new ArrayList<>();
				
				for(JsonElement e2 : lvl.get("features").getAsJsonArray()) {
					classLevel.features.add(e2.getAsJsonObject().get("index").getAsString());
				}
				
				classLevel.spellcasting = new ArrayList<>(Collections.nCopies(11, 0));
				if(lvl.has("spellcasting")) {
					JsonObject spellcasting = lvl.get("spellcasting").getAsJsonObject();
					for(int x = 1; x <= 9; x++) {
						classLevel.spellcasting.set(x, spellcasting.has("spell_slots_level_" + x) ?
								spellcasting.get("spell_slots_level_" + x).getAsInt() : 0);
					}
					classLevel.spellcasting.set(0, spellcasting.has("spells_known") ?
							spellcasting.get("spells_known").getAsInt() : 0);
					classLevel.spellcasting.set(10, spellcasting.has("cantrips_known") ?
							spellcasting.get("cantrips_known").getAsInt() : 0);
				}
				
				classLevel.classSpecific = new HashMap<>();
				JsonObject specific = lvl.get("class_specific").getAsJsonObject();
				for(String key : specific.keySet()) {
					if(specific.get(key).isJsonObject()) {
						for(String subKey : specific.get(key).getAsJsonObject().keySet())
							classLevel.classSpecific.put(subKey, specific.get(key).getAsJsonObject().get(subKey).getAsInt());
					} else if(specific.get(key).isJsonArray()) {
						for(JsonElement e2 : specific.get(key).getAsJsonArray()) { // sorcerer edge case
							classLevel.classSpecific.put("spell_slot_level_" + e2.getAsJsonObject().get("spell_slot_level").getAsInt(), 
									e2.getAsJsonObject().get("sorcery_point_cost").getAsInt());
						}
					} else if(specific.get(key).getAsJsonPrimitive().isBoolean())
						classLevel.classSpecific.put(key, specific.get(key).getAsBoolean() ? 1 : 0);
					else
						classLevel.classSpecific.put(key, specific.get(key).getAsInt());
				}
			}
		}
		
		return dndClass;
	}

}
