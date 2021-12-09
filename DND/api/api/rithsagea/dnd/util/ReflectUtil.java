package api.rithsagea.dnd.util;

import java.lang.reflect.Field;

public class ReflectUtil {
	@SuppressWarnings("unchecked")
	public static <T> T getField(Object obj, String fieldName, Class<T> type) {
		try {
			Class<?> clazz = obj.getClass();
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return (T) field.get(obj);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void setField(Object obj, String fieldName, Object val) {
		try {
			Field field = obj.getClass().getField(fieldName);
			field.setAccessible(true);
			field.set(obj, val);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
