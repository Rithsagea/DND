package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api5e.data.Proficiency;

public class ProficiencyAdapter implements JsonDeserializer<Proficiency> {

	@Override
	public Proficiency deserialize(JsonElement elem, Type type, JsonDeserializationContext context) {
		Proficiency proficiency = new Proficiency();
		JsonObject obj = elem.getAsJsonObject();
		
		proficiency.id = obj.get("index").getAsString();
		proficiency.name = obj.get("name").getAsString();
		proficiency.type = obj.get("type").getAsString();
		
		return proficiency;
	}
}
