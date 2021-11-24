package com.rithsagea.dnd.api.types.extras;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

@JsonAdapter(OptionsAdapter.class)
public class OptionItem<T> implements OptionType {

	public OptionItem() { }
	public OptionItem(String type, T value) { this.type = type; this.value = value; }
	
	public T value;
	
	@SerializedName("__type")
	public String type;
	
	public String toString() { return type + ":" + value; }

}
