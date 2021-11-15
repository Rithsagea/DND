package com.rithsagea.dnd.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtil {
	public static JsonElement get(JsonElement elem, String path) {
		if(elem == null) return null;
		if(path == null || path.isEmpty()) return elem;
		
		int split = path.indexOf('/');
		
		String token;
		if(split == -1) {
			token = path;
			split = path.length() - 1;
		} else {
			token = path.substring(0, split);
		}
		
		if(token.isEmpty()) return get(elem, path.substring(split + 1));
		if(elem.isJsonObject()) return get(elem.getAsJsonObject().get(token), path.substring(split + 1));
		if(elem.isJsonArray()) return get(elem.getAsJsonArray().get(Integer.parseInt(token)), path.substring(split + 1));
		return null;
	}
	
	public static String getString(JsonElement elem, String path) {
		JsonElement e = get(elem, path);
		if(e == null) return "";
		return e.getAsString();
	}
	
	public static int getInt(JsonElement elem, String path) {
		JsonElement e = get(elem, path);
		if(e == null) return 0;
		return e.getAsInt();
	}
	
	public static boolean getBool(JsonElement elem, String path) {
		JsonElement e = get(elem, path);
		if(e == null) return false;
		return e.getAsBoolean();
	}
	
	public static JsonObject getObject(JsonElement elem, String path) {
		JsonElement e = get(elem, path);
		if(e == null) return null;
		return e.getAsJsonObject();
	}
	
	public static JsonArray getArray(JsonElement elem, String path) {
		JsonElement e = get(elem, path);
		if(e == null) return new JsonArray();
		return e.getAsJsonArray();
	}
	
	public static boolean exists(JsonElement elem, String path) {
		return get(elem, path) != null;
	}
}
