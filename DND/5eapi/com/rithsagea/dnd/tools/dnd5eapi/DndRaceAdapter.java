package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.extra.AbilityBonus;
import com.rithsagea.dnd.api5e.data.extra.ProficiencyOptions;
import com.rithsagea.dnd.api5e.data.races.Dnd5eRace;
import com.rithsagea.dnd.util.JsonUtil;

public class DndRaceAdapter implements JsonDeserializer<Dnd5eRace> {

	@Override
	public Dnd5eRace deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
		Dnd5eRace race = new Dnd5eRace();
		
		race.id = JsonUtil.getString(elem, "index");
		race.name = JsonUtil.getString(elem, "name");
		race.speed = JsonUtil.getInt(elem, "speed");
		
		race.bonuses = new ArrayList<>();
		for(JsonElement e1 : JsonUtil.getArray(elem, "ability_bonuses")) {
			AbilityBonus bonus = new AbilityBonus();
			race.bonuses.add(bonus);
			
			bonus.ability = JsonUtil.getString(e1, "ability_score/index");
			bonus.bonus = JsonUtil.getInt(e1, "bonus");
		}
		
		race.alignment = JsonUtil.getString(elem, "alignment");
		race.age = JsonUtil.getString(elem, "age");
		race.size = JsonUtil.getString(elem, "size");
		race.sizeDescription = JsonUtil.getString(elem, "size_description");
		
		race.proficiencies = new ArrayList<>();
		for(JsonElement e1 : JsonUtil.getArray(elem, "starting_proficiencies")) {
			race.proficiencies.add(JsonUtil.getString(e1, "index"));
		}
		
		race.proficiencyOptions = new ProficiencyOptions();
		race.proficiencyOptions.count = JsonUtil.getInt(elem, "starting_proficiency_options/choose");
		race.proficiencyOptions.proficiencies = new ArrayList<>();
		for(JsonElement e2 : JsonUtil.getArray(elem, "starting_proficiency_options/from")) {
			race.proficiencyOptions.proficiencies.add(JsonUtil.getString(e2, "index"));
		}
		
		race.languages = new ArrayList<>();
		for(JsonElement e1 : JsonUtil.getArray(elem, "langauges")) {
			race.languages.add(JsonUtil.getString(e1, "index"));
		}
		
		race.languageDesc = JsonUtil.getString(elem, "language_desc");
		
		race.traits = new ArrayList<>();
		for(JsonElement e1 : JsonUtil.getArray(elem, "traits")) {
			race.traits.add(JsonUtil.getString(e1, "index"));
		}
		
		race.subraces = new ArrayList<>();
		for(JsonElement e1 : JsonUtil.getArray(elem, "subraces")) {
			race.subraces.add(JsonUtil.getString(e1, "index"));
		}
		
		return race;
	}

}
