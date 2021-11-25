package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.Condition;
import com.rithsagea.dnd.util.JsonUtil;

public class ConditionAdapter implements JsonDeserializer<Condition> {

	@Override
	public Condition deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		Condition condition = new Condition();
		
		condition.id = JsonUtil.getString(arg0, "index");
		condition.name = JsonUtil.getString(arg0, "name");
		
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		for(JsonElement e : JsonUtil.getArray(arg0, "desc")) {
			builder.append(prefix);
			builder.append(e.getAsString());
			prefix = "\n";
		}
		condition.description = builder.toString();
		return condition;
	}
	
}
