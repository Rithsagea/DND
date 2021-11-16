package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.SourceRegistry;

public class SkeletonRunner {
	
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	public static void main(String[] args) {
		SourceRegistry reg = new SourceRegistry(SOURCE_DIRECTORY);
		reg.init();
	}
}
