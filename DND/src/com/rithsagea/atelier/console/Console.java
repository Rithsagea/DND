package com.rithsagea.atelier.console;

import java.util.HashMap;
import java.util.Map;

public abstract class Console {
	private Map<String, Command> commands;
	
	public Console() {
		commands = new HashMap<>();
	}
	
	public void registerCommand(Command command) {
		registerCommand(command, command.getLabel());
		for(String alias : command.getAliases())
			registerCommand(command, alias);
	}
	
	private void registerCommand(Command command, String label) {
		if(!commands.containsKey(label))
			commands.put(label, command);
	}
	
	public void receiveMessage(Message message) {
		String text = message.getText();
		String[] args = text.split(" ");
		String label = args[0];
		
		Command cmd = commands.get(label);
		if(cmd != null) cmd.execute(message, args);
	}
	
	public abstract void sendMessage(Message message);
}
