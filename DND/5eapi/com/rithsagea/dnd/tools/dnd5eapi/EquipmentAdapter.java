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
import com.rithsagea.dnd.api5e.data.equipment.Dnd5eEquipment;
import com.rithsagea.dnd.api5e.data.equipment.EquipmentAmmunition;
import com.rithsagea.dnd.api5e.data.equipment.EquipmentArmor;
import com.rithsagea.dnd.api5e.data.equipment.EquipmentGear;
import com.rithsagea.dnd.api5e.data.equipment.EquipmentMount;
import com.rithsagea.dnd.api5e.data.equipment.EquipmentPack;
import com.rithsagea.dnd.api5e.data.equipment.EquipmentTool;
import com.rithsagea.dnd.api5e.data.equipment.EquipmentWeapon;
import com.rithsagea.dnd.api5e.data.extra.CoinQuantity;
import com.rithsagea.dnd.api5e.data.extra.EquipmentStack;
import com.rithsagea.dnd.util.JsonUtil;

public class EquipmentAdapter implements JsonDeserializer<Dnd5eEquipment> {

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
				ammunition.quantity = JsonUtil.getInt(elem, "quantity");
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
			
			equipment.weaponCategory = JsonUtil.getString(elem, "weapon_category");
			equipment.weaponRange = JsonUtil.getString(elem, "weapon_range");
			equipment.categoryRange = JsonUtil.getString(elem, "category_range");
			
			if(obj.has("damage")) {
				temp = obj.get("damage").getAsJsonObject();
				equipment.damageDice = temp.get("damage_dice").getAsString();
				equipment.damageType = temp.get("damage_type").getAsJsonObject()
						.get("index").getAsString();
			}
			
			temp = JsonUtil.getObject(elem, "range");
			equipment.normalRange = JsonUtil.getInt(temp, "normal");
			equipment.longRange = JsonUtil.getInt(temp, "long");
			
			equipment.properties = new ArrayList<>();
			for(JsonElement e : JsonUtil.getArray(elem, "properties")) {
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
			
			temp = JsonUtil.getObject(elem, "armor_class");
			if(temp != null) {
				equipment.armorClass = temp.get("base").getAsInt();
				equipment.dexBonus = temp.get("dex_bonus").getAsBoolean();
				equipment.maxBonus = temp.get("max_bonus").isJsonNull() ? 0 : temp.get("max_bonus").getAsInt();
			}
			
			equipment.minStrength = JsonUtil.getInt(elem, "str_minimum");
			equipment.stealthDisadvantage = JsonUtil.getBool(elem, "stealth_disadvantage");
			
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
	public Dnd5eEquipment deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject obj = elem.getAsJsonObject();
		Dnd5eEquipment equipment = null;
		
		String category = obj.get("equipment_category").getAsJsonObject().get("index").getAsString();
		
		Type t;
		switch(category) {
		
		case "adventuring-gear": t = EquipmentGear.class; break;
		
		case "tools": t = EquipmentTool.class; break;
		case "mounts-and-vehicles": t = EquipmentMount.class; break;
		case "weapon": t = EquipmentWeapon.class; break;
		case "armor": t = EquipmentArmor.class; break;
		
		case "potion":
		case "wand":
		case "ring":
		case "staff":
		case "scroll":
		case "rod":
		case "wondrous-items":
		case "ammunition": t = null; break;

		default: t = null; System.out.println("Unknown Equipment Category: " + category);
		
		}
		
		equipment = t == null ? new Dnd5eEquipment() : gson.fromJson(elem, t);
		
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
		
		equipment.weight = JsonUtil.getInt(elem, "weight");
		equipment.cost = gson.fromJson(obj.get("cost"), CoinQuantity.class);
		
		
		return equipment;
	}

}
