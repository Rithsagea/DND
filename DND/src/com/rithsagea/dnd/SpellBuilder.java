package com.rithsagea.dnd;

import java.io.File;
import java.util.TreeMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Spell;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.Dnd5eSpell;
import com.rithsagea.dnd.util.JsonUtil;

public class SpellBuilder {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		for(Dnd5eSpell model : data5e.Spell.values()) {
			Spell spell = new Spell();
			
			spell.id = model.id;
			spell.name = model.name;
			spell.description = model.description;
			spell.higherDescription = model.higherDescription;
			spell.range = model.range;
			spell.components = model.components;
			spell.materials = model.materials;
			
			spell.ritual = model.ritual;
			spell.duration = model.duration;
			spell.castingTime = model.castingTime;
			spell.level = model.level;
			spell.attackType = model.attackType;
			if(spell.attackType.isEmpty()) spell.attackType = null;
			
			JsonElement elem = model.damage;
			if(elem != null) {
				spell.damageType = JsonUtil.getString(elem, "damage_type/index");
				spell.damageValues = new TreeMap<>();
				JsonObject obj = JsonUtil.getObject(elem, "damage_at_slot_level");
				if(obj == null) obj = JsonUtil.getObject(elem, "damage_at_character_level");
				for(String key : obj.keySet()) {
					spell.damageValues.put(key, JsonUtil.getString(obj, key));
				}
			}
			spell.school = model.school;
			
			book.register(spell);
		}
		
		SourceRegistry.saveBooks();
	}
}
