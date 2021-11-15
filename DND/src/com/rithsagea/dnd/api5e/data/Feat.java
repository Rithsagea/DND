package com.rithsagea.dnd.api5e.data;

import com.google.gson.JsonElement;

//TODO manually input these ugh
public class Feat extends DndItem {
	public String name;
	public JsonElement prerequisites;
	public String description;
}
