package com.rithsagea.atelier.console;

import java.util.HashMap;
import java.util.Map;

import com.rithsagea.atelier.AtelierBot;

public abstract class ModularCommand extends Command {

	public ModularCommand(AtelierBot bot) {
		super(bot);
	}

	private Map<String, Command> subCommands = new HashMap<>();
	
	private void registerSubCommand(String label, Command command) {
		if(!subCommands.containsKey(label))
			subCommands.put(label, command);
	}
	
	protected void registerSubCommand(Command command) {
		registerSubCommand(command.getLabel(), command);
		if(command.getAliases() != null)
		for(String alias : command.getAliases())
			registerSubCommand(alias, command);
	}
	
	public abstract String getLabel();
	public abstract String[] getAliases();

	public abstract void executeEmpty(Message message);
	
	@Override
	public void execute(Message message, String[] args) {
		if(args.length <= 1) {
			executeEmpty(message);
		} else {
			String[] subArgs = new String[args.length - 1];
			String label = args[1];
			System.arraycopy(args, 1, subArgs, 0, subArgs.length);
			
			Command cmd = subCommands.get(label);
			
			if(cmd != null) {
				cmd.execute(message, subArgs);
			}
		}
	}

}
