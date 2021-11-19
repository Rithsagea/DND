package com.rithsagea.dnd.api5e.data;

import java.util.List;

import com.google.gson.JsonElement;
import com.rithsagea.dnd.api.types.IndexedItem;

public class Spell extends IndexedItem {
	public String name;
	public String description;
	public String higherDescription;
	public String range;
	public List<String> components;
	public String materials;
	
	public boolean ritual;
	public String duration;
	public boolean concentration;
	public String castingTime;
	public int level;
	public String attackType;
	
	public JsonElement damage;
	public String school;
}
