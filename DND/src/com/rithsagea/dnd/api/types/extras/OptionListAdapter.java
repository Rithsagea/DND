package com.rithsagea.dnd.api.types.extras;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class OptionListAdapter implements JsonSerializer<OptionList> {

	@Override
	public JsonElement serialize(OptionList src, Type typeOfSrc, JsonSerializationContext context) {
		return context.serialize(src.values);
	}

}
