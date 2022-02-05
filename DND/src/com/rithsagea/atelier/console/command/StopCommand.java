package com.rithsagea.atelier.console.command;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Command;
import com.rithsagea.atelier.console.Message;
import com.rithsagea.atelier.console.TextMessage;

public class StopCommand extends Command {

	public StopCommand(AtelierBot bot) {
		super(bot);
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
		AtelierBot bot = getBot();
		
		bot.getConsole().sendMessage(new TextMessage("Stopping Atelier!"));
		bot.stop();
	}

}
