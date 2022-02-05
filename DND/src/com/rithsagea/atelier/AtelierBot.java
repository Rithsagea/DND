package com.rithsagea.atelier;

import java.io.File;

import com.rithsagea.atelier.console.Console;
import com.rithsagea.atelier.console.command.CharacterCommand;
import com.rithsagea.atelier.console.command.StopCommand;

import api.rithsagea.atelier.AtelierDatabase;
import api.rithsagea.atelier.Config;

public class AtelierBot {
	
	private Console console;
	
	private Config config;
	private AtelierDatabase database;
	
	private boolean running = true;
	
	public AtelierBot(Console console) {
		this.console = console;
		config = new Config(new File("config.properties"));
		database = new AtelierDatabase(config.getDatabaseUrl());
		
		registerCommands();
	}
	
	private void registerCommands() {
		console.registerCommand(new StopCommand(this));
		console.registerCommand(new CharacterCommand(this));
	}
	
	public Console getConsole() {
		return console;
	}
	
	public AtelierDatabase getDatabase() {
		return database;
	}
	
	public void stop() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}
