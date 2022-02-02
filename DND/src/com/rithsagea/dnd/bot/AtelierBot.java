package com.rithsagea.dnd.bot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.rithsagea.dnd.bot.commands.StopCommand;

public class AtelierBot {
	
	private boolean running;
	private Map<String, Command> commands;
	
	public AtelierBot() {
		running = true;
		commands = new HashMap<>();
		
		registerCommand(new StopCommand(this));
	}
	
	public void registerCommand(Command command) {
		commands.put(command.getLabel(), command);
		
		if(command.getAliases() != null)
		for(String alias : command.getAliases()) {
			commands.put(alias, command);
		}
	}
	
	public void stop() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void receiveMessage(String msg) {
		String[] args = msg.split(" ");
		String label = args[0];
		
		Command cmd = commands.get(label);
		if(cmd != null) cmd.execute(args);
	}
	
	public void submitMessage(Message msg) {
		System.out.println(msg.getText());
	}
	
	// RUNNER
	public static void main(String[] args) {
		AtelierBot bot = new AtelierBot();
		
		Scanner scanner = new Scanner(System.in);
		
		while(bot.isRunning()) {
			System.out.print("> ");
			String input = scanner.nextLine();
			
			bot.receiveMessage(input);
		}
		
		scanner.close();
	}
}
