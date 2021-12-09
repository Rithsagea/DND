package api.rithsagea.dnd.util;

import java.util.Collection;

import api.rithsagea.dnd.types.IndexedItem;

public class WordUtil {
	
	//TODO decide on how to do this
	public static String createHyperlink(IndexedItem item) {
		return String.format("[%s]", item.getClass().getSimpleName() + "." + item.getId());
	}
	
	public static String createHyperlinkedText(Object... objs) {
		StringBuilder builder = new StringBuilder();
		
		for(Object obj : objs) {
			if(obj instanceof IndexedItem) {
				builder.append(createHyperlink((IndexedItem) obj));
			} else {
				builder.append(obj);
			}
		}
		return builder.toString();
	}
	
	public static String toPascalCase(String text) {
		StringBuilder builder = new StringBuilder();
		for(String str : text.toLowerCase().split("[\\W_]+")) {
			builder.append(capitalizeFirst(str.toLowerCase()));
		}
		
		return builder.toString();
	}
	
	public static String toTitleCase(String text) {
		StringBuilder builder = new StringBuilder();
		String prefix = "";
		for(String str : text.toLowerCase().split("[\\W_]+")) {
			builder.append(prefix);
			builder.append(capitalizeFirst(str.toLowerCase()));
			prefix = " ";
		}
		
		return builder.toString();
	}
	
	public static String initials(String text) {
		StringBuilder builder = new StringBuilder();
		for(String word : toTitleCase(text).split("[\\W_]+")) {
			builder.append(Character.toUpperCase(word.charAt(0)));
		}
		
		return builder.toString();
	}
	
	public static String formatId(String text) {
		return toPascalCase(text);
	}
	
	public static String capitalizeFirst(String text) {
		return Character.toUpperCase(text.charAt(0)) + text.substring(1);
	}
	
	public static String commaList(Collection<String> items) {
		if(items == null || items.isEmpty()) return "";
		
		StringBuilder builder = new StringBuilder();
		
		int counter = 1;
		String prefix = "";
		for(String item : items) {
			counter++;
			builder.append(prefix);
			builder.append(item);
			prefix = counter < items.size() ? ", " :
					items.size() <= 2 ? " and " : ", and ";
		}
		
		return builder.toString();
	}
}
