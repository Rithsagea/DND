package legacy.rithsagea.dnd.api;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class SourceRegistry {
	
	private static final SourceRegistry INSTANCE = new SourceRegistry();
	public static SourceRegistry getInstance() { return INSTANCE; }
	
	private File dir;
	private Map<String, SourceBook> books;
	private Map<Class<?>, Map<String, Object>> registry;
	
	private SourceRegistry () { }
	
	/**
	 * Reads in header file TODO make this
	 * @param dir the directory of the source books
	 */
	public void init(File dir) {
		this.dir = dir;
		
		if(!dir.exists()) {
			dir.mkdir();
		}
	}
	
	/**
	 * Reads in and loads all the source books
	 */
	public void load() {
		registry = new HashMap<>();
		books = new HashMap<>();
		
		if(dir.isDirectory()) {
			for(File file : dir.listFiles()) {
				SourceBook book = new SourceBook(file);
				book.init();
				
				books.put(book.name, book);
			}
		}
		
		books.values().stream().sorted().forEach((book) -> {
			book.load();
			loadBook(book);
		});
	}
	
	private void loadBook(SourceBook book) {
		for(String key1 : SourceBook.DATA_TYPES.keySet()) {
			Class<?> clazz = SourceBook.DATA_TYPES.get(key1);
			
			if(!registry.containsKey(clazz)) registry.put(clazz, new TreeMap<>());
			Map<String, Object> values = book.data.get(clazz);
			Map<String, Object> registryMap = registry.get(clazz);
			
			if(values != null)
			for(Entry<String, Object> entry : values.entrySet()) {
				if(!registryMap.containsKey(entry.getKey())) // TODO add override flag
					registryMap.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public void saveBooks() {
		for(SourceBook book : books.values()) {
			book.save();
		}
	}
	
	public File getDir() {
		return dir;
	}
	
	public Map<String, SourceBook> getBooks() {
		return books;
	}
	
	public Set<String> getKeys(Class<?> clazz) {
		return registry.containsKey(clazz) ? registry.get(clazz).keySet() : null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T> getItems(Class<T> clazz) {
		return (Collection<T>) registry.get(clazz).values();
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getItem(String id, Class<T> clazz) {
		if(!registry.containsKey(clazz)) return null; // registry doesn't exist
		return (T) (registry.get(clazz).get(id));
	}
	
	public void registerItem(String id, Object obj) {
		Class<?> clazz = obj.getClass();
		if(!registry.containsKey(clazz))
			registry.put(clazz, new HashMap<>());
		registry.get(clazz).put(id, obj);
	}
}
