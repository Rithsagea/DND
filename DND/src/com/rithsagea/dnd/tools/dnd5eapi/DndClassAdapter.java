package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api.data.DndClass;
import com.rithsagea.dnd.api.data.extra.EquipmentOption;
import com.rithsagea.dnd.api.data.extra.EquipmentStack;
import com.rithsagea.dnd.api.data.extra.ProficiencyOptions;

public class DndClassAdapter implements JsonDeserializer<DndClass> {

	@Override
	public DndClass deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		DndClass dndClass = new DndClass();
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
							.get("index").getAsJsonObject().getAsString());
				}
				if(e2.getAsJsonObject().has("equipment_option")) {
					option.equipment.add(e2.getAsJsonObject().get("equipment_option").getAsJsonObject()
							.get("from").getAsJsonObject().get("equipment_category").getAsJsonObject()
							.get("index").getAsString()); // TODO unpack this
				}
			}
		}
		
		// TODO class levels
		// TODO multiclassing
		
		dndClass.subclasses = new ArrayList<>();
		for(JsonElement e1 : obj.get("subclasses").getAsJsonArray()) {
			dndClass.subclasses.add(e1.getAsJsonObject().get("index").getAsString());
		}
		
		
		
		return dndClass;
	}

}
