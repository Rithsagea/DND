package com.rithsagea.dnd.api.types.extras;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rithsagea.dnd.util.JsonUtil;

public class OptionsAdapter implements JsonDeserializer<OptionType> {

	@Override
	public OptionType deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		
		OptionItem<?> item = null;
		Class<?> clazz = null;
		
		switch(JsonUtil.getString(elem, "type")) {
		
		case "option": return context.deserialize(elem, Options.class);
		default: item = new OptionItem<String>(); clazz = String.class; break;
		
		}
		
		item.type = JsonUtil.getString(elem, "type");
		item.value = context.deserialize(JsonUtil.get(elem, "value"), clazz);
		
		return item;
	}

//	@Override
//	public JsonElement serialize(OptionType item, Type type, JsonSerializationContext context) {
//		return null;
//	}
}
