package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.rithsagea.dnd.api.misc.Proficiency;
import com.rithsagea.util.JsonAdapter;

public class ProficiencyAdapter implements JsonAdapter<Proficiency> {

	@Override
	public Proficiency deserialize(JsonElement elem, Type type, JsonDeserializationContext context) {
		Proficiency proficiency = new Proficiency();
		JsonObject obj = elem.getAsJsonObject();
		
		proficiency.id = obj.get("index").getAsString();
		proficiency.name = obj.get("name").getAsString();
		proficiency.type = obj.get("type").getAsString();
		
		return proficiency;
	}

	@Override
	public JsonElement serialize(Proficiency obj, Type type, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
