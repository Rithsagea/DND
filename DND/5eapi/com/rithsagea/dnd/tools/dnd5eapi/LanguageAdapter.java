package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api.types.Language;

public class LanguageAdapter implements JsonDeserializer<Language> {

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
}
