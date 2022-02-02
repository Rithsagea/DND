package com.rithsagea.dnd.bot.commands;

import com.rithsagea.dnd.bot.Command;
import com.rithsagea.dnd.bot.AtelierBot;

public class StopCommand implements Command {

	private AtelierBot bot;
	
	public StopCommand(AtelierBot bot) {
		this.bot = bot;
	}
	
	@Override
	public String getLabel() {
		return "stop";
	}

	@Override
	public String[] getAliases() {
		return new String[] {"close", "exit", "quit"};
	}

	@Override
	public void execute(String[] args) {
		bot.submitMessage(new StopMessage());
		bot.stop();
	}

}
