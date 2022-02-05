package com.rithsagea.atelier;

import java.io.File;

import api.rithsagea.atelier.AtelierDatabase;
import api.rithsagea.atelier.CharacterSheet;
import api.rithsagea.atelier.Config;

public class DBTest {
	public static void main(String[] args) {
		Config config = new Config(new File("config.properties"));
		AtelierDatabase db = new AtelierDatabase(config.getDatabaseUrl());
		
		CharacterSheet sheet = new CharacterSheet();
		sheet.setName("Rithsagea");
		db.updateSheet(sheet);
		
		for(CharacterSheet x: db.getSheets()) {
			System.out.println(x.getName() + ": " + x.getId());
		}
	}
}
