package com.rithsagea.atelier.console;

import com.rithsagea.atelier.AtelierBot;

public abstract class BaseCommand extends Command {

	private String label;
	private String[] aliases;
	
	public BaseCommand(AtelierBot bot, String label, String[] aliases) {
		super(bot);
		
		this.label = label;
		this.aliases = aliases;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String[] getAliases() {
		return aliases;
	}
}
