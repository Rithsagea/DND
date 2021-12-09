package api.rithsagea.dnd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.types.IndexedItem;

public class TextManager {

	private static final TextManager INSTANCE = new TextManager(new File("data/lang/en_US.lang"));

	public static TextManager getInstance() {
		return INSTANCE;
	}

	private Properties messages;
	private File file;

	private TextManager(File file) {
		this.file = file;

		messages = new Properties() {

			private static final long serialVersionUID = 1L;

			@Override
			public Set<Object> keySet() {
			    return Collections.unmodifiableSet(new TreeSet<Object>(super.keySet()));
			}

			@Override
			public Set<Map.Entry<Object, Object>> entrySet() {

			    Set<Map.Entry<Object, Object>> set1 = super.entrySet();
			    Set<Map.Entry<Object, Object>> set2 = new LinkedHashSet<Map.Entry<Object, Object>>(set1.size());

			    Iterator<Map.Entry<Object, Object>> iterator = set1.stream().sorted(new Comparator<Map.Entry<Object, Object>>() {

			        @Override
			        public int compare(java.util.Map.Entry<Object, Object> o1, java.util.Map.Entry<Object, Object> o2) {
			            return o1.getKey().toString().compareTo(o2.getKey().toString());
			        }
			    }).iterator();

			    while (iterator.hasNext())
			        set2.add(iterator.next());

			    return set2;
			}

			@Override
			public synchronized Enumeration<Object> keys() {
			    return Collections.enumeration(new TreeSet<Object>(super.keySet()));
			    }
			};
	}

	public void load() {
		try {
			messages.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			messages.store(new FileOutputStream(file), "text labels for stuffs");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getMessage(IndexedItem obj, String key) {
		return getMessage(String.format("%s.%s.%s", obj.getClass().getSimpleName(), obj.getId(), key));
	}

	public String getMessage(String key) {
		String msgKey = key;

		if (messages.containsKey(msgKey)) {
			return messages.getProperty(key);
		}

		messages.setProperty(msgKey, "");

		return msgKey;
	}
	
	public void setMessage(IndexedItem obj, String key, String message) {
		messages.setProperty(String.format("%s.%s.%s", obj.getClass().getSimpleName(), obj.getId(), key), message);
	}
	
	public void setMessage(String key, String message) {
		messages.setProperty(key, message);
	}
}
