package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api.data.classes.DndSubclass;
import com.rithsagea.dnd.api.data.classes.DndSubclassLevel;

public class DndSubclassAdapter implements JsonDeserializer<DndSubclass> {

	private Gson gson = new Gson();
	
	@Override
	public DndSubclass deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		
		DndSubclass subclass = new DndSubclass();
		JsonObject obj = elem.getAsJsonObject();
		
		subclass.id = obj.get("index").getAsString();
		subclass.parentClass = obj.get("class").getAsJsonObject().get("index").getAsString();
		subclass.name = obj.get("name").getAsString();
		subclass.flavor = obj.get("subclass_flavor").getAsString();
		
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		for(JsonElement e : obj.get("desc").getAsJsonArray()) {
			builder.append(prefix);
			builder.append(e.getAsString());
			prefix = "\n";
		}
		
		subclass.levels = new ArrayList<>(Collections.nCopies(20, null));
		for(JsonElement e1 : gson.fromJson(Dnd5eApiTool.get("/subclasses/" + subclass.id + "/levels"), JsonArray.class)) {
			DndSubclassLevel lvl = new DndSubclassLevel();
			JsonObject level = e1.getAsJsonObject();
			
			lvl.id = level.get("index").getAsString();
			lvl.level = level.get("level").getAsInt();
			lvl.features = new ArrayList<>();
			for(JsonElement e2 : level.get("features").getAsJsonArray()) {
				lvl.features.add(e2.getAsJsonObject().get("index").getAsString());
			}
			subclass.levels.set(lvl.level - 1, lvl);
			
			lvl.specific = new HashMap<>();
			if(level.has("subclass_specific"))
			for(String key : level.get("subclass_specific").getAsJsonObject().keySet()) {
				lvl.specific.put(key, level.get("subclass_specific").getAsJsonObject().get(key).getAsInt());
			}
		}
		
		return subclass;
	}

}
