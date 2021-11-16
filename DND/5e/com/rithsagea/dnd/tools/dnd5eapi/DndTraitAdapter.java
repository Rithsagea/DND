package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.extra.Choice;
import com.rithsagea.dnd.api5e.data.races.DndRaceTrait;
import com.rithsagea.dnd.util.JsonUtil;

public class DndTraitAdapter implements JsonDeserializer<DndRaceTrait> {

	@Override
	public DndRaceTrait deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		DndRaceTrait trait = new DndRaceTrait();
		
		trait.id = JsonUtil.getString(elem, "index");
		trait.name = JsonUtil.getString(elem, "name");
		
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		for(JsonElement e : JsonUtil.getArray(elem, "desc")) {
			builder.append(prefix);
			builder.append(e.getAsString());
			prefix = "\n";
		}
		trait.description = builder.toString();
		
		trait.proficiencies = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "proficiencies")) {
			trait.proficiencies.add(JsonUtil.getString(e, "index"));
		}
		
		trait.proficiencyChoices = new Choice<>();
		trait.proficiencyChoices.count = JsonUtil.getInt(elem, "proficiency_choices/choose");
		trait.proficiencyChoices.type = "proficiency";
		trait.proficiencyChoices.choices = new ArrayList<>();
		
		for(JsonElement e : JsonUtil.getArray(elem, "proficiency_choices/from")) {
			trait.proficiencyChoices.choices.add(JsonUtil.getString(e, "index"));
		}
	
		
		trait.traitSpecific = JsonUtil.get(elem, "trait_specific");
		
		return trait;
	}

}
