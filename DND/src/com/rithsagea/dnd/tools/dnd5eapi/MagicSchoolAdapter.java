package com.rithsagea.dnd.tools.dnd5eapi;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.api5e.data.MagicSchool;
import com.rithsagea.dnd.util.JsonUtil;

public class MagicSchoolAdapter implements JsonDeserializer<MagicSchool> {

	@Override
	public MagicSchool deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		MagicSchool magicSchool = new MagicSchool();
		
		magicSchool.id = JsonUtil.getString(elem, "index");
		magicSchool.name = JsonUtil.getString(elem, "name");
		magicSchool.description = JsonUtil.getString(elem, "desc");
		
		return magicSchool;
	}
	
}
