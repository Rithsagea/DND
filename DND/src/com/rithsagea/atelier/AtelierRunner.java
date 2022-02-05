package com.rithsagea.atelier;

import java.util.Scanner;

import com.rithsagea.atelier.console.Console;
import com.rithsagea.atelier.console.TextConsole;
import com.rithsagea.atelier.console.TextMessage;

public class AtelierRunner {
	
	private static Console console;
	private static AtelierBot bot;
	
	private static void debug(String message) {
		System.out.println("> " + message);
		message(message);
	}
	
	private static void message(String message) {
		console.receiveMessage(new TextMessage(message));
	}
	
	public static void main(String[] args) {
		console = new TextConsole();
		bot = new AtelierBot(console);
		
		Scanner scanner = new Scanner(System.in);
		
		debug("char new");
		debug("char list");
		debug("char select bb325126-063b-6e58-bb5e-029b33fbd643");
		debug("char");
		
		while(bot.isRunning()) {
			System.out.print("> ");
			message(scanner.nextLine());
		}
		
		scanner.close();
	}
}
