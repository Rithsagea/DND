package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.Datapack;
import com.rithsagea.dnd.tools.dnd5eapi.Dnd5eApiTool;

public class Dnd5eApiRunner {
	public static void main(String[] args) {
		Dnd5eApiTool tool = new Dnd5eApiTool("https://www.dnd5eapi.co/api");
		
		File file = new File("5e.json");
		
		Datapack data5e = new Datapack();
		data5e = Datapack.loadDatapack(file);
		
//		data5e.registerAbilityScores(tool.getAbilityScores());
//		data5e.registerSkills(tool.getSkills());
//		data5e.registerProficiencies(tool.getProficiencies());
//		data5e.registerLanguages(tool.getLanguages());
//		data5e.registerAlignments(tool.getAlignments());
//		data5e.registerEquipment(tool.getEquipment());
//		Datapack.saveDatapack(file, data5e);
		
		
		
		System.out.println();
	}
}
