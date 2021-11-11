package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.rithsagea.dnd.api.misc.Alignment;
import com.rithsagea.util.JsonAdapter;

public class AlignmentAdapter implements JsonAdapter<Alignment> {

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

	@Override
	public JsonElement serialize(Alignment obj, Type type, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
