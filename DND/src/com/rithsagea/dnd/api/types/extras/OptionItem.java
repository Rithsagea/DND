package com.rithsagea.dnd.api.types.extras;

import com.google.gson.annotations.JsonAdapter;

@JsonAdapter(OptionsAdapter.class)
public class OptionItem<T> implements OptionType {

	public T value;
	public String type;
	
	public String typeName() { return type; }
	
	public String toString() { return type + ":" + value; }

}
