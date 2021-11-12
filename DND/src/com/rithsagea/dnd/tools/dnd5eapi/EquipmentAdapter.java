package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api.data.equipment.Equipment;
import com.rithsagea.dnd.api.data.equipment.EquipmentAmmunition;
import com.rithsagea.dnd.api.data.equipment.EquipmentArmor;
import com.rithsagea.dnd.api.data.equipment.EquipmentGear;
import com.rithsagea.dnd.api.data.equipment.EquipmentMount;
import com.rithsagea.dnd.api.data.equipment.EquipmentPack;
import com.rithsagea.dnd.api.data.equipment.EquipmentTool;
import com.rithsagea.dnd.api.data.equipment.EquipmentWeapon;
import com.rithsagea.dnd.api.data.extra.CoinQuantity;
import com.rithsagea.dnd.api.data.extra.EquipmentStack;

public class EquipmentAdapter implements JsonDeserializer<Equipment> {

	private class GearAdapter implements JsonDeserializer<EquipmentGear> {

		@Override
		public EquipmentGear deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			EquipmentGear equipment;
			JsonObject temp, obj = elem.getAsJsonObject();
			String category = obj.get("gear_category").getAsJsonObject()
					.get("index").getAsString();
			
			switch(category) {
			
			case "standard-gear":
			case "holy-symbols":
			case "kits":
			case "arcane-foci":
			case "druidic-foci":
				equipment = new EquipmentGear();
				break;
			case "equipment-packs":
				EquipmentPack pack = new EquipmentPack();
				equipment = pack;
				pack.contents = new ArrayList<>(); // TODO: unfold these when adding to inven
				for(JsonElement e : obj.get("contents").getAsJsonArray()) {
					EquipmentStack stack = new EquipmentStack();
					temp = e.getAsJsonObject();
					stack.equipment = temp.get("item").getAsJsonObject().get("index").getAsString();
					stack.count = temp.get("quantity").getAsInt();
				}
				break;
			case "ammunition":
				EquipmentAmmunition ammunition = new EquipmentAmmunition();
				equipment = ammunition;
				ammunition.quantity = obj.get("quantity").getAsInt();
				break;
			default:
				equipment = new EquipmentGear();
				System.out.println("Unkown Gear Category: " + category); break;
			
			}
			
			equipment.gearCategory = category;
			
			return equipment;
		}
		
	}
	
	private class ToolAdapter implements JsonDeserializer<EquipmentTool> {

		@Override
		public EquipmentTool deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			EquipmentTool equipment = new EquipmentTool();
			
			equipment.toolCategory = elem.getAsJsonObject().get("tool_category").getAsString();
			
			return equipment;
		}
		
	}
	
	private class MountAdapter implements JsonDeserializer<EquipmentMount> {

		@Override
		public EquipmentMount deserialize(JsonElement elem, Type type, JsonDeserializationContext arg2)
				throws JsonParseException {
			EquipmentMount equipment = new EquipmentMount();
			
			equipment.vehicleCategory = elem.getAsJsonObject().get("vehicle_category").getAsString();
			
			return equipment;
		}
		
	}
	
	private class WeaponAdapter implements JsonDeserializer<EquipmentWeapon> {

		@Override
		public EquipmentWeapon deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject temp, obj = elem.getAsJsonObject();
			EquipmentWeapon equipment = new EquipmentWeapon();
			
			equipment.weaponCategory = obj.get("weapon_category").getAsString();
			equipment.weaponRange = obj.get("weapon_range").getAsString();
			equipment.categoryRange = obj.get("category_range").getAsString();
			
			if(obj.has("damage")) {
				temp = obj.get("damage").getAsJsonObject();
				equipment.damageDice = temp.get("damage_dice").getAsString();
				equipment.damageType = temp.get("damage_type").getAsJsonObject()
						.get("index").getAsString();
			}
			
			temp = obj.get("range").getAsJsonObject();
			equipment.normalRange = temp.get("normal").isJsonNull() ? 0 : temp.get("normal").getAsInt();
			equipment.longRange = temp.get("long").isJsonNull() ? 0 : temp.get("long").getAsInt();
			
			equipment.properties = new ArrayList<>();
			for(JsonElement e : obj.get("properties").getAsJsonArray()) {
				equipment.properties.add(e.getAsJsonObject().get("index").getAsString());
			}
			
			if(equipment.properties.contains("thrown")) {
				temp = obj.get("throw_range").getAsJsonObject();
				equipment.normalThrowRange = temp.get("normal").getAsInt();
				equipment.longThrowRange = temp.get("long").getAsInt();
			}
			
			return equipment;
		}
		
	}
	
	private class ArmorAdapter implements JsonDeserializer<EquipmentArmor> {

		@Override
		public EquipmentArmor deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
				throws JsonParseException {
			JsonObject temp, obj = elem.getAsJsonObject();
			EquipmentArmor equipment = new EquipmentArmor();
			
			equipment.armorCategory = obj.get("equipment_category").getAsJsonObject()
					.get("index").getAsString();
			
			temp = obj.get("armor_class").getAsJsonObject();
			equipment.armorClass = temp.get("base").getAsInt();
			equipment.dexBonus = temp.get("dex_bonus").getAsBoolean();
			equipment.maxBonus = temp.get("max_bonus").isJsonNull() ? 0 : temp.get("max_bonus").getAsInt();
			
			equipment.minStrength = obj.get("str_minimum").getAsInt();
			equipment.stealthDisadvantage = obj.get("stealth_disadvantage").getAsBoolean();
			
			return equipment;
		}
		
	}
	
	private Gson gson;
	
	public EquipmentAdapter() {
		gson = new GsonBuilder()
				.registerTypeAdapter(EquipmentGear.class, new GearAdapter())
				.registerTypeAdapter(EquipmentTool.class, new ToolAdapter())
				.registerTypeAdapter(EquipmentMount.class, new MountAdapter())
				.registerTypeAdapter(EquipmentWeapon.class, new WeaponAdapter())
				.registerTypeAdapter(EquipmentArmor.class, new ArmorAdapter())
				.create();
	}
	
	@Override
	public Equipment deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject obj = elem.getAsJsonObject();
		Equipment equipment = null;
		
		String category = obj.get("equipment_category").getAsJsonObject().get("index").getAsString();
		
		Type t;
		switch(category) {
		
		case "adventuring-gear": t = EquipmentGear.class; break;
		
		case "tools": t = EquipmentTool.class; break;
		case "mounts-and-vehicles": t = EquipmentMount.class; break;
		case "weapon": t = EquipmentWeapon.class; break;
		case "armor": t = EquipmentArmor.class; break;
		
		default: t = null; System.out.println("Unknown Equipment Category: " + category);
		
		}
		
		equipment = t == null ? new Equipment() : gson.fromJson(elem, t);
		
		equipment.id = obj.get("index").getAsString();
		equipment.name = obj.get("name").getAsString();
		equipment.category = category;
		if(obj.has("desc")) {
			StringBuilder builder = new StringBuilder();
			String prefix = "";
			for(JsonElement line : obj.get("desc").getAsJsonArray()) {
				builder.append(prefix);
				builder.append(line.getAsString());
				prefix = "\n";
			}
			equipment.description = builder.toString();
		}
		
		equipment.weight = obj.has("weight") ? obj.get("weight").getAsInt() : 0;
		equipment.cost = gson.fromJson(obj.get("cost"), CoinQuantity.class);
		
		
		return equipment;
	}

}
