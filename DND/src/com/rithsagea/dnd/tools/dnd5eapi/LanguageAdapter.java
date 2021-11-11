package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.rithsagea.dnd.api.misc.Language;
import com.rithsagea.util.JsonAdapter;

public class LanguageAdapter implements JsonAdapter<Language> {

	@Override
	public Language deserialize(JsonElement elem, Type type, JsonDeserializationContext context) {
		Language language = new Language();
		JsonObject obj = elem.getAsJsonObject();
		
		language.id = obj.get("index").getAsString();
		language.name = obj.get("name").getAsString();
		
		if(obj.get("script").isJsonNull())
			language.script = "None";
		else
			language.script = obj.get("script").getAsString();
		
		return language;
	}

	@Override
	public JsonElement serialize(Language obj, Type type, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
