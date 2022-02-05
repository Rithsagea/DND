package com.rithsagea.atelier.console.command;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Message;
import com.rithsagea.atelier.console.ModularCommand;
import com.rithsagea.atelier.console.TextMessage;

public class CharacterCommand extends ModularCommand {
	
	public CharacterCommand(AtelierBot bot) {
		super(bot);
	}

	@Override
	public String getLabel() {
		return "character";
	}

	@Override
	public String[] getAliases() {
		return new String[] {"char"};
	}

	@Override
	public void executeEmpty(Message message) {
		AtelierBot bot = getBot();
		bot.getConsole().sendMessage(new TextMessage("Dummy Character " + message.getSender()));
	}
}
