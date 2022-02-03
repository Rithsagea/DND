package com.rithsagea.atelier.console.command;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Command;
import com.rithsagea.atelier.console.Message;
import com.rithsagea.atelier.console.TextMessage;

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
		return new String[] {"quit", "close", "exit"};
	}

	@Override
	public void execute(Message message, String[] args) {
		bot.getConsole().sendMessage(new TextMessage("Stopping Atelier!"));
		bot.stop();
	}

}
