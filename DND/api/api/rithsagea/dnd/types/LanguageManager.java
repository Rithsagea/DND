package api.rithsagea.dnd.types;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import api.rithsagea.dnd.types.aspects.Feature;
import api.rithsagea.dnd.types.aspects.Trait;

public class LanguageManager {
	private static LanguageManager INSTANCE = new LanguageManager();
	public static LanguageManager getInstance() { return INSTANCE; }
	private LanguageManager() { loadMessages(); }
	
	private Locale locale = Locale.US;
	private String path = "data/lang/";
	private Properties messages;
	private Set<String> missing;
	
	public void setLocale(Locale locale) { this.locale = locale; }
	public void setPath(String path) { this.path = path; }
	public Locale getLocale() { return locale; }
	public String getPath() { return path; }
	
	private void loadMessages() {
		try {
			File file = new File(path + locale + ".lang");
			if(!file.exists()) file.createNewFile();
			messages = new Properties();
			messages.load(new FileInputStream(file));
			missing = new LinkedHashSet<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key) {
		if(!messages.containsKey(key)) missing.add(key + "=");
		return messages.getProperty(key, key);
	}
	
	public String get(IndexedItem item, String key) {
		String className = item.getClass().getSimpleName();
		if(item instanceof Trait) className = "Trait";
		if(item instanceof AbstractRace) className = "Race";
		if(item instanceof Feature) className = "Feature";
		if(item instanceof AbstractClass) className = "Class";
		
		return get(String.format("%s.%s.%s", className, item.getId(), key));
	}
	
	public Set<String> missing() {
		return Collections.unmodifiableSet(missing);
	}
}
