package com.rithsagea.atelier.console;

import com.rithsagea.atelier.AtelierBot;

public abstract class Command {
	
	private AtelierBot bot;
	
	public Command(AtelierBot bot) {
		this.bot = bot;
	}
	
	public AtelierBot getBot() {
		return bot;
	}
	
	public abstract String getLabel();
	public abstract String[] getAliases();
	
	public abstract void execute(Message message, String[] args);
}
