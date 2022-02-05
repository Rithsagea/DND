package com.rithsagea.atelier;

import com.rithsagea.atelier.console.Console;
import com.rithsagea.atelier.console.command.CharacterCommand;
import com.rithsagea.atelier.console.command.StopCommand;

import api.rithsagea.atelier.CharacterDatabase;

public class AtelierBot {
	
	private Console console;
	private CharacterDatabase characterDatabase;
	private UserDatabase userDatabase;
	
	private boolean running = true;
	
	public AtelierBot(Console console) {
		this.console = console;
		
		characterDatabase = new CharacterDatabase();
		userDatabase = new UserDatabase();
		
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
	
	public UserDatabase getUserDatabase() {
		return userDatabase;
	}
	
	public void stop() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
}
