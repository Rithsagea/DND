package com.rithsagea.dnd.api;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.rithsagea.util.JsonAdapter;

public class RegistryAdapter implements JsonAdapter<Registry<?>> {

	private Gson gson = new GsonBuilder().create();
	
	@Override
	public JsonElement serialize(Registry<?> obj, Type type, JsonSerializationContext context) {
		return gson.toJsonTree(obj.registry);
	}

	@Override
	public Registry<?> deserialize(JsonElement elem, Type type, JsonDeserializationContext context) {
		JsonObject obj = new JsonObject();
		obj.add("registry", elem);
		return gson.fromJson(obj, type);
	}

}
