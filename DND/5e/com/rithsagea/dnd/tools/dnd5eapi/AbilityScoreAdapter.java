package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api.types.AbilityScore;

public class AbilityScoreAdapter implements JsonDeserializer<AbilityScore> {

	@Override
	public AbilityScore deserialize(JsonElement elem, Type type, JsonDeserializationContext context) {
		AbilityScore abilityScore = new AbilityScore();
		JsonObject obj = elem.getAsJsonObject();
		JsonArray arr;
		
		abilityScore.id = obj.get("index").getAsString();
		abilityScore.shortName = obj.get("name").getAsString();
		abilityScore.name = obj.get("full_name").getAsString();
		
		arr = obj.get("desc").getAsJsonArray();
		abilityScore.description = arr.get(0).getAsString();
		abilityScore.usageDescription = arr.get(1).getAsString();
		
		return abilityScore;
	}
}
