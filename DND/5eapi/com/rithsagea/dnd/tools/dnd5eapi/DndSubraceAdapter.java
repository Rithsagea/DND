package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.extra.AbilityBonus;
import com.rithsagea.dnd.api5e.data.extra.Choice;
import com.rithsagea.dnd.api5e.data.races.Dnd5eSubrace;
import com.rithsagea.dnd.util.JsonUtil;

public class DndSubraceAdapter implements JsonDeserializer<Dnd5eSubrace> {

	@Override
	public Dnd5eSubrace deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		Dnd5eSubrace subrace = new Dnd5eSubrace();
		
		subrace.id = JsonUtil.getString(elem, "index");
		subrace.name = JsonUtil.getString(elem, "name");
		subrace.race = JsonUtil.getString(elem, "race/index");
		subrace.description = JsonUtil.getString(elem, "desc");
		
		subrace.bonuses = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "ability_bonuses")) {
			AbilityBonus bonus = new AbilityBonus();
			subrace.bonuses.add(bonus);
			
			bonus.ability = JsonUtil.getString(e, "ability_score/index");
			bonus.bonus = JsonUtil.getInt(e, "bonus");
		}
		
		subrace.proficiencies = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "starting_proficiencies")) {
			subrace.proficiencies.add(JsonUtil.getString(e, "index"));
		}
		
		subrace.languages = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "languages")) {
			subrace.languages.add(JsonUtil.getString(e, "index"));
		}
		
		subrace.languageChoices = new Choice<>();
		for(JsonElement e1 : JsonUtil.getArray(elem, "language_choices")) {
			subrace.languageChoices.count = JsonUtil.getInt(e1, "choose");
			subrace.languageChoices.type = "language";
			subrace.languageChoices.choices = new ArrayList<>();
			
			for(JsonElement e2: JsonUtil.getArray(e1, "from")) {
				subrace.languageChoices.choices.add(JsonUtil.getString(e2, "index"));
			}
		}
		
		subrace.traits = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "racial_traits")) {
			subrace.traits.add(JsonUtil.getString(e, "index"));
		}
		
		return subrace;
	}

}
