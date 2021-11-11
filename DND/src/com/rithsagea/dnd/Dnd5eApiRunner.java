package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.DndRegistry;
import com.rithsagea.dnd.tools.dnd5eapi.Dnd5eApiTool;

public class Dnd5eApiRunner {
	public static void main(String[] args) {
		Dnd5eApiTool tool = new Dnd5eApiTool("https://www.dnd5eapi.co/api");
		
		File file = new File("5e.json");
		
		DndRegistry.loadRegistry(file);
		DndRegistry.registerAbilityScores(tool.getAbilityScores());
		DndRegistry.saveRegistry(file);
		
		System.out.println();
	}
}
