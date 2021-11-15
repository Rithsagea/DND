package com.rithsagea.dnd.api5e.data.races;

import java.util.List;

import com.google.gson.JsonElement;
import com.rithsagea.dnd.api5e.data.DndItem;
import com.rithsagea.dnd.api5e.data.extra.Choice;

public class DndRaceTrait extends DndItem {
	public String name;
	public String description;
	
	public List<String> proficiencies;
	public Choice<String> proficiencyChoices;
	public JsonElement traitSpecific; // kill me
}
