package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.Rule;
import com.rithsagea.dnd.util.JsonUtil;

public class RuleAdapter implements JsonDeserializer<Rule> {

	@Override
	public Rule deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
		Rule rule = new Rule();
		
		rule.id = JsonUtil.getString(elem, "index");
		rule.name = JsonUtil.getString(elem, "name");
		rule.description = JsonUtil.getString(elem, "desc");
		
		rule.subsections = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "subsections")) {
			rule.subsections.add(JsonUtil.getString(e, "index"));
		}
		
		return rule;
	}
	
}
