package com.rithsagea.dnd.api;

import java.io.File;

public class SourceBook {

	private File dir;
	
	public SourceBook(File dir) {
		this.dir = dir;
	}
	
	public File getDir() {
		return dir;
	}
}
