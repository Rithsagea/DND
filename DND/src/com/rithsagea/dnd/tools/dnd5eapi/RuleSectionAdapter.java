package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.RuleSection;
import com.rithsagea.dnd.util.JsonUtil;

public class RuleSectionAdapter implements JsonDeserializer<RuleSection> {

	@Override
	public RuleSection deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
		RuleSection rule = new RuleSection();
		
		rule.id = JsonUtil.getString(elem, "index");
		rule.name = JsonUtil.getString(elem, "name");
		rule.description = JsonUtil.getString(elem, "desc");
		
		return rule;
	}
	
}
