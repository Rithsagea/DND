package com.rithsagea.dnd.api.types.extras;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ExtrasAdapter implements JsonSerializer<Map<String, Object>>, JsonDeserializer<Map<String, Object>> {

	private static final String TYPE_LABEL = "extra_type";
	
	@Override
	public Map<String, Object> deserialize(JsonElement elem, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject obj = elem.getAsJsonObject();
		Map<String, Object> extras = new TreeMap<>();
		
		for(Entry<String, JsonElement> entry : obj.entrySet()) {
			if(entry.getValue().isJsonPrimitive()) {
				JsonPrimitive val = entry.getValue().getAsJsonPrimitive();
				
				if(val.isNumber()) { extras.put(entry.getKey(), val.getAsNumber()); continue; }
				if(val.isBoolean()) { extras.put(entry.getKey(), val.getAsBoolean()); continue; }
				if(val.isString()) { extras.put(entry.getKey(), val.getAsString()); continue; }
			} else {
				try {
					JsonObject extra = entry.getValue().getAsJsonObject();
					Class<?> clazz = Class.forName(extra.remove(TYPE_LABEL).getAsString());
					
					extras.put(entry.getKey(), context.deserialize(extra, clazz));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return extras;
	}

	@Override
	public JsonElement serialize(Map<String, Object> extras, Type type, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		
		for(Entry<String, Object> entry : extras.entrySet()) {
			Object extra = entry.getValue();
			
			if(extra instanceof Number) { obj.addProperty(entry.getKey(), (Number) extra); continue; }
			if(extra instanceof String) { obj.addProperty(entry.getKey(), (String) extra); continue; }
			if(extra instanceof Boolean) { obj.addProperty(entry.getKey(), (Boolean) extra); continue; }
		
			JsonElement val = context.serialize(extra);
			val.getAsJsonObject().addProperty(TYPE_LABEL, extra.getClass().getName());
		}
		
		return obj;
	}
}
