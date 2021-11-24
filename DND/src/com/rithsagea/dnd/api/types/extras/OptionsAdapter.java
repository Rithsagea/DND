package com.rithsagea.dnd.api.types.extras;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.rithsagea.dnd.util.JsonUtil;

public class OptionsAdapter implements JsonDeserializer<OptionType>{

	@Override
	public OptionType deserialize(JsonElement elem, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		
		if(elem.isJsonArray()) return new OptionList(context.deserialize(elem, new TypeToken<ArrayList<OptionType>>() {}.getType())); 
		
		OptionItem<?> item = null;
		Class<?> clazz = null;
		
		switch(JsonUtil.getString(elem, "__type")) {
		
		case "option": return context.deserialize(elem, Options.class);
		case "item": clazz = ItemStack.class; break;
		case "ability_score": clazz = AbilityScoreRequirement.class; break;
		default: clazz = String.class; break;
		
		}
		
		item = new OptionItem<>();
		item.type = JsonUtil.getString(elem, "__type");
		item.value = context.deserialize(JsonUtil.get(elem, "value"), clazz);
		
		return item;
	}
}
