package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api.misc.Skill;

public class SkillAdapter implements JsonDeserializer<Skill> {

	@Override
	public Skill deserialize(JsonElement elem, Type type, JsonDeserializationContext context) {
		Skill skill = new Skill();
		JsonObject obj = elem.getAsJsonObject();
		JsonArray arr;
		
		skill.id = obj.get("index").getAsString();
		skill.name = obj.get("name").getAsString();
		
		arr = obj.get("desc").getAsJsonArray();
		skill.description = arr.get(0).getAsString();
		
		obj = obj.get("ability_score").getAsJsonObject();
		skill.abilityScore = obj.get("index").getAsString();
		
		return skill;
	}
}
