package api.rithsagea.atelier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private File configFile;
	private Properties prop;
	
	private static final String DB_URL_KEY = "databaseUrl";
	
	public Config(File configFile) {
		this.configFile = configFile;
		prop = new Properties();
		
		if(!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		load();
		setDefault(DB_URL_KEY, "");
		save();
	}
	
	private void setDefault(String key, String val) {
		if(!prop.containsKey(key)) {
			prop.setProperty(key, val);
		}
	}
	
	public void load() {
		try {
			prop.load(new FileReader(configFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			prop.store(new FileWriter(configFile), "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDatabaseUrl() {
		return prop.getProperty(DB_URL_KEY, "");
	}
}
