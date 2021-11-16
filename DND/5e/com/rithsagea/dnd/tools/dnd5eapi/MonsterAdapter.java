package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.Monster;
import com.rithsagea.dnd.util.JsonUtil;

public class MonsterAdapter implements JsonDeserializer<Monster> {

	@Override
	public Monster deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
		Monster monster = new Monster();
		
		monster.id = JsonUtil.getString(elem, "index");
		monster.name = JsonUtil.getString(elem, "name");
		monster.size = JsonUtil.getString(elem, "size");
		monster.type = JsonUtil.getString(elem, "type");
		monster.subtype = JsonUtil.getString(elem, "subtype");
		monster.alignment = JsonUtil.getString(elem, "alignment");
		monster.armorClass = JsonUtil.getInt(elem, "armor_class");
		monster.hitPoints = JsonUtil.getInt(elem, "hit_points");
		monster.hitDice = JsonUtil.getString(elem, "hit_dice");
		
		monster.forms = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "forms")) {
			monster.forms.add(JsonUtil.getString(e, "forms"));
		}
		
		monster.speed = JsonUtil.get(elem, "speed");
		monster.strength = JsonUtil.getInt(elem, "strength");
		monster.dexterity = JsonUtil.getInt(elem, "dexterity");
		monster.constitution = JsonUtil.getInt(elem, "constitution");
		monster.intelligence = JsonUtil.getInt(elem, "intelligence");
		monster.wisdom = JsonUtil.getInt(elem, "wisdom");
		monster.charisma = JsonUtil.getInt(elem, "charisma");
		
		monster.proficiencies = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "proficiencies")) {
			monster.proficiencies.add(JsonUtil.getString(e, "index"));
		}
		
		monster.damageVulnerabilities = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "damage_vulnerabilities")) {
			monster.proficiencies.add(JsonUtil.getString(e, "index"));
		}
		monster.damageResistances = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "damage_resistances")) {
			monster.proficiencies.add(JsonUtil.getString(e, "index"));
		}
		monster.damageImmunities = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "damage_immunities")) {
			monster.proficiencies.add(JsonUtil.getString(e, "index"));
		}
		monster.conditionImmunities = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "condition_immunities")) {
			monster.proficiencies.add(JsonUtil.getString(e, "index"));
		}
		
		monster.senses = JsonUtil.get(elem, "senses");
		monster.languages = JsonUtil.getString(elem, "languages");
		monster.challengeRating = JsonUtil.getDec(elem, "challenge_rating");
		
		monster.specialAbilities = new ArrayList<>();
		JsonUtil.getArray(elem, "special_abilities").forEach(monster.specialAbilities::add);
		
		monster.actions = new ArrayList<>();
		JsonUtil.getArray(elem, "actions").forEach(monster.actions::add);
		
		monster.legendaryActions = new ArrayList<>();
		JsonUtil.getArray(elem, "legendary_actions").forEach(monster.legendaryActions::add);
		
		return monster;
	}

}
