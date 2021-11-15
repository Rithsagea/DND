package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api.data.Spell;
import com.rithsagea.dnd.util.JsonUtil;

public class SpellAdapter implements JsonDeserializer<Spell> {

	@Override
	public Spell deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
		Spell spell = new Spell();
		
		spell.id = JsonUtil.getString(elem, "index");
		spell.name = JsonUtil.getString(elem, "name");
		
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		for(JsonElement e : JsonUtil.getArray(elem, "desc")) {
			builder.append(prefix);
			builder.append(e.getAsString());
			prefix = "\n";
		}
		spell.description = builder.toString();
		
		builder = new StringBuilder();
		prefix = "";
		for(JsonElement e : JsonUtil.getArray(elem, "higher_level")) {
			builder.append(prefix);
			builder.append(e.getAsString());
			prefix = "\n";
		}
		spell.higherDescription = builder.toString();
		
		spell.range = JsonUtil.getString(elem, "range");
		
		spell.components = new ArrayList<>();
		for(JsonElement e : JsonUtil.getArray(elem, "components")) {
			spell.components.add(e.getAsString());
		}
		
		spell.materials = JsonUtil.getString(elem, "materials");
		spell.ritual = JsonUtil.getBool(elem, "ritual");
		spell.duration = JsonUtil.getString(elem, "duration");
		spell.concentration = JsonUtil.getBool(elem, "concentration");
		spell.castingTime = JsonUtil.getString(elem, "casting_time");
		spell.level = JsonUtil.getInt(elem, "level");
		spell.attackType = JsonUtil.getString(elem, "attack_type");
		spell.damage = JsonUtil.get(elem, "damage");
		spell.school = JsonUtil.getString(elem, "school/index");
		
		return spell;
	}
	
}
