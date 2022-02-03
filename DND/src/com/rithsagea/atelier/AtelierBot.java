package com.rithsagea.atelier;

import java.util.Scanner;

import com.rithsagea.atelier.console.Console;
import com.rithsagea.atelier.console.TextConsole;
import com.rithsagea.atelier.console.TextMessage;
import com.rithsagea.atelier.console.command.StopCommand;

public class AtelierBot {
	
	private Console console;
	
	private boolean running = true;
	
	public AtelierBot(Console console) {
		this.console = console;
		
		registerCommands();
	}
	
	private void registerCommands() {
		console.registerCommand(new StopCommand(this));
	}
	
	public Console getConsole() {
		return console;
	}
	
	public void stop() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	
	public static void main(String[] args) {
		TextConsole console = new TextConsole();
		AtelierBot bot = new AtelierBot(console);
		
		Scanner scanner = new Scanner(System.in);
		
		while(bot.isRunning()) {
			System.out.print("> ");
			console.receiveMessage(new TextMessage(scanner.nextLine()));
		}
		
		scanner.close();
	}
}
