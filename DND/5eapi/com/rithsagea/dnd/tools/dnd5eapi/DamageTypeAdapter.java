package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.DamageType;
import com.rithsagea.dnd.util.JsonUtil;

public class DamageTypeAdapter implements JsonDeserializer<DamageType> {

	@Override
	public DamageType deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		DamageType damageType = new DamageType();
		
		damageType.id = JsonUtil.getString(elem, "index");
		damageType.name = JsonUtil.getString(elem, "name");
		
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		for(JsonElement e : JsonUtil.getArray(elem, "desc")) {
			builder.append(prefix);
			builder.append(e.getAsString());
			prefix = "\n";
		}
		
		damageType.description = builder.toString();
		
		return damageType;
	}
	
}
