package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api.data.Alignment;

public class AlignmentAdapter implements JsonDeserializer<Alignment> {

	@Override
	public Alignment deserialize(JsonElement elem, Type type, JsonDeserializationContext context) {
		Alignment alignment = new Alignment();
		JsonObject obj = elem.getAsJsonObject();
		
		alignment.id = obj.get("index").getAsString();
		alignment.name = obj.get("name").getAsString();
		alignment.shortName = obj.get("abbreviation").getAsString();
		alignment.description = obj.get("desc").getAsString();
		
		return alignment;
	}
}
