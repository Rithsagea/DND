package com.rithsagea.dnd.api.types;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class IndexedItemFactory implements TypeAdapterFactory {
	
	private class IndexedItemAdapter<T> extends TypeAdapter<T> {

		private Class<T> clazz;
		private Map<String, Field> fieldMap;
		
		private IndexedItemAdapter(Class<T> clazz) {
			this.clazz = clazz;
			fieldMap = new TreeMap<>();
			for(Field field : clazz.getFields()) {
				fieldMap.put(field.getName(), field);
			}
		}
		
		@Override
		public void write(JsonWriter out, T value) throws IOException {
			for(Entry<String, Field> field : fieldMap.entrySet()) {
				//switch case here for stuffs
			}
			
			//extra here
		}

		@SuppressWarnings("unchecked")
		@Override
		public T read(JsonReader in) throws IOException {
			try {
				IndexedItem item = (IndexedItem) clazz.newInstance();
				
				//... do stuff with fieldMap
				
				return (T) item;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		if(type.getRawType().isAssignableFrom(IndexedItem.class)) {
			return new IndexedItemAdapter<T>((Class<T>) type.getClass());
		}
		
		return null;
	}

}
