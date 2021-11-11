package com.rithsagea.util;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public interface JsonAdapter<T> extends JsonSerializer<T>, JsonDeserializer<T> {
	public T deserialize(JsonElement elem, Type type, JsonDeserializationContext context);
	public JsonElement serialize(T obj, Type type, JsonSerializationContext context);
}
