package com.rithsagea.dnd.api5e.data.equipment;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class EquipmentAdapter implements JsonDeserializer<Dnd5eEquipment> {
	
	private Gson gson = new GsonBuilder().create();
	
	@Override
	public Dnd5eEquipment deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		String category = elem.getAsJsonObject().get("category").getAsString();
		switch(category) {
		
		case "tools": return gson.fromJson(elem, EquipmentTool.class);
		case "weapon": return gson.fromJson(elem, EquipmentWeapon.class);
		case "armor": return gson.fromJson(elem, EquipmentArmor.class);
		case "adventuring-gear": return gson.fromJson(elem, EquipmentGear.class);
		case "mounts-and-vehicles": return gson.fromJson(elem, EquipmentMount.class);
		default: return gson.fromJson(elem, Dnd5eEquipment.class);
		
		}
	}
}
