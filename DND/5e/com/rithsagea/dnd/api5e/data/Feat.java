package com.rithsagea.dnd.api5e.data;

import com.google.gson.JsonElement;
import com.rithsagea.dnd.api.types.IndexedItem;

//TODO manually input these ugh
public class Feat extends IndexedItem {
	public String name;
	public JsonElement prerequisites;
	public String description;
}
