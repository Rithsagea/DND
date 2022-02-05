package com.rithsagea.atelier.console;

import java.util.HashMap;
import java.util.Map;

public abstract class ModularCommand implements Command {

	private Map<String, Command> subCommands = new HashMap<>();
	
	private void registerSubCommand(String label, Command command) {
		if(!subCommands.containsKey(label))
			subCommands.put(label, command);
	}
	
	protected void registerSubCommand(Command command) {
		registerSubCommand(command.getLabel(), command);
		for(String alias : command.getAliases())
			registerSubCommand(alias, command);
	}
	
	public abstract String getLabel();
	public abstract String[] getAliases();

	public abstract void executeEmpty(Message message);
	
	@Override
	public void execute(Message message, String[] args) {
		if(args.length == 0) {
			executeEmpty(message);
		} else {
			String[] subArgs = new String[args.length - 1];
			String label = args[0];
			System.arraycopy(args, 1, subArgs, 0, subArgs.length);
			
			Command cmd = subCommands.get(label);
			
			if(cmd != null) {
				cmd.execute(message, subArgs);
			}
		}
	}

}
