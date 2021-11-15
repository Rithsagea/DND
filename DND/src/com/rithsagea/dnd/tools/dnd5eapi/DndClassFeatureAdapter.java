package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.classes.DndClassFeature;
import com.rithsagea.dnd.util.JsonUtil;

public class DndClassFeatureAdapter implements JsonDeserializer<DndClassFeature> {

	@Override
	public DndClassFeature deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		DndClassFeature feature = new DndClassFeature();
		JsonObject obj = elem.getAsJsonObject();
		
		feature.id = obj.get("index").getAsString();
		feature.name = obj.get("name").getAsString();
		
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		for(JsonElement e1 : obj.get("desc").getAsJsonArray()) {
			builder.append(prefix);
			builder.append(e1.getAsString());
			prefix = "\n";
		}
		feature.description = builder.toString();
		
		if(obj.has("feature_specific")) {
			JsonObject specific = obj.get("feature_specific").getAsJsonObject();
			if(specific.has("subfeature_options")) {
				feature.optionCount = JsonUtil.getInt(specific, "subfeature_options/choose");
				feature.options = new ArrayList<>();
				feature.optionType = "feature";
				for(JsonElement e1 : JsonUtil.getArray(specific, "subfeature_options/from")) {
					feature.options.add(JsonUtil.getString(e1, "index"));
				}
			} else {
				feature.optionCount = JsonUtil.getInt(specific, "expertise_options/choose");
				feature.options = new ArrayList<>();
				feature.optionType = "proficiency";
				for(JsonElement e1 : JsonUtil.getArray(specific, "expertise_options/from")) {
					feature.options.add(JsonUtil.getString(e1, "index"));
				}
			}
		} else {
			feature.optionCount = -1;
			feature.options = new ArrayList<>();
			feature.optionType = "None";
		}
		
		return feature;
	}
	
}
