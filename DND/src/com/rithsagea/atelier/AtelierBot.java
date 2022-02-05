package com.rithsagea.atelier;

import java.io.File;

import com.rithsagea.atelier.console.Console;
import com.rithsagea.atelier.console.command.CharacterCommand;
import com.rithsagea.atelier.console.command.StopCommand;

import api.rithsagea.atelier.AtelierDatabase;
import api.rithsagea.atelier.CharacterDatabase;
import api.rithsagea.atelier.Config;

public class AtelierBot {
	
	private Console console;
	private CharacterDatabase characterDatabase;
	
	private Config config;
	private AtelierDatabase userDatabase;
	
	private boolean running = true;
	
	public AtelierBot(Console console) {
		this.console = console;
		
		config = new Config(new File("config.properties"));
		
		characterDatabase = new CharacterDatabase();
		userDatabase = new AtelierDatabase(config.getDatabaseUrl());
		
		registerCommands();
	}
	
	private void registerCommands() {
		console.registerCommand(new StopCommand(this));
		console.registerCommand(new CharacterCommand(this));
	}
	
	public Console getConsole() {
		return console;
	}
	
	public CharacterDatabase getCharacterDatabase() {
		return characterDatabase;
	}
	
	public AtelierDatabase getUserDatabase() {
		return userDatabase;
	}
	
	public void stop() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}
