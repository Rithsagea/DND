package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.rithsagea.dnd.api.misc.AbilityScore;
import com.rithsagea.util.JsonAdapter;

public class AbilityScoreAdapter implements JsonAdapter<AbilityScore> {

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
		
		arr = obj.get("skills").getAsJsonArray();
		List<String> skills = new ArrayList<String>();
		for(JsonElement e : arr) skills.add(e.getAsJsonObject().get("index").getAsString());
		abilityScore.skills = skills;
		
		return abilityScore;
	}

	@Override
	public JsonElement serialize(AbilityScore obj, Type type, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
