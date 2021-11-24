package com.rithsagea.dnd.api.types;

import java.util.Map;

import com.google.gson.annotations.JsonAdapter;

public class IndexedItem {
	public String id;
	
	@JsonAdapter(ExtrasAdapter.class)
	public Map<String, Object> extra;
}
