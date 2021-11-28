package com.rithsagea.dnd.api5e;

import java.io.File;

import com.rithsagea.dnd.tools.dnd5eapi.Dnd5eApiTool;

public class Dnd5eApiRunner {
	public static void main(String[] args) {
		Dnd5eApiTool.setUrl("https://www.dnd5eapi.co/api");
		
		File file = new File("5e.json");
		File backup = new File("5e Backup.json");
		
		Datapack data5e = new Datapack();
//		data5e = Datapack.loadDatapack(file);
		
//		data5e.registerAbilityScores(Dnd5eApiTool.getAbilityScores());
//		data5e.registerSkills(Dnd5eApiTool.getSkills());
//		data5e.registerProficiencies(Dnd5eApiTool.getProficiencies());
//		data5e.registerLanguages(Dnd5eApiTool.getLanguages());
//		data5e.registerAlignments(Dnd5eApiTool.getAlignments());
//		data5e.registerEquipment(Dnd5eApiTool.getEquipment());
//		data5e.registerEquipment(Dnd5eApiTool.getMagicEquipment());
//		data5e.registerClasses(Dnd5eApiTool.getClasses());
//		data5e.registerSubclasses(Dnd5eApiTool.getSubclasses());
		data5e.registerClassFeatures(Dnd5eApiTool.getClassFeatures());
//		data5e.registerSubraces(Dnd5eApiTool.getSubraces());
//		data5e.registerRaces(Dnd5eApiTool.getRaces());
//		data5e.registerTraits(Dnd5eApiTool.getTraits());
//		data5e.registerSpells(Dnd5eApiTool.getSpells());
//		data5e.registerMonsters(Dnd5eApiTool.getMonsters());
//		data5e.registerConditions(Dnd5eApiTool.getConditions());
//		data5e.registerDamageTypes(Dnd5eApiTool.getDamageTypes());
//		data5e.registerMagicSchools(Dnd5eApiTool.getMagicSchools());
//		data5e.registerRules(Dnd5eApiTool.getRules());
//		data5e.registerRuleSections(Dnd5eApiTool.getRuleSections());
		
		data5e.registerDatapack(Datapack.loadDatapack(backup));
		
		Datapack.saveDatapack(file, data5e);
		
		System.out.println();
	}
}
