package com.rithsagea.dnd.api;

import java.io.File;
import java.util.List;

public class SourceRegistry {
	
	private File dir;
	private List<SourceBook> books;
	
	public SourceRegistry(File dir) {
		this.dir = dir;
		
		if(!dir.exists()) {
			dir.mkdir();
		}
	}
	
	public void init() {
		if(dir.isDirectory()) {
			for(File file : dir.listFiles()) {
				books.add(new SourceBook(file));
			}
		}
	}
	
	public File getDir() {
		return dir;
	}
}
