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
		
//		debug("char new");
		debug("char list");
		debug("char select 61fed562108eec72453e414e");
		debug("char");
		debug("exit");
		
		while(bot.isRunning()) {
			System.out.print("> ");
			message(scanner.nextLine());
		}
		
		scanner.close();
	}
}
