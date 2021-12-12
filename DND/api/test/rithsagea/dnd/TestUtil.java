package test.rithsagea.dnd;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestUtil {
	
	private static List<String> serializeObject(Object obj) {
		List<String> res = new ArrayList<>();
		
		for(Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				Object val = field.get(obj);
				if(val instanceof Map) {
					res.add(field.getName() + ":");
					
					for(String line : serializeMap((Map<?, ?>) val)) {
						res.add("\t" + line);
					}
					
				} else if (val instanceof Collection) {
					res.add(field.getName() + ": ");
					for(String line : serializeCollection((Collection<?>) val)) {
						res.add("\t" + line);
					}
				} else {
					res.add(field.getName() + ": " + val);
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
	
	private static List<String> serializeMap(Map<?, ?> map) {
		List<String> res = new ArrayList<>();
		for(Entry<?, ?> entry : map.entrySet()) {
			res.add(entry.getKey() + ": " + entry.getValue());
		}
		
		return res;
	}
	
	private static List<String> serializeCollection(Collection<?> col) {
		List<String> res = new ArrayList<>();
		for(Object obj : col) res.add(obj.toString());
		
		return res;
	}
	
	/**
	 * Reflectively gets details from this object
	 * (is technically nsfw lmao, never again)
	 * @param obj the object to inspect
	 * @return the object's details
	 */
	public static String toString(Object obj) {
		StringBuilder builder = new StringBuilder();
		
		String prefix = "";
		for(String line : serializeObject(obj)) {
			builder.append(prefix);
			builder.append(line);
			prefix = "\n";
		}
		
		return builder.toString();
	}
	
	public static Object getField(Object obj, String name) {
		Field field;
		try {
			field = obj.getClass().getDeclaredField(name);
			field.setAccessible(true);
			return field.get(obj);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
