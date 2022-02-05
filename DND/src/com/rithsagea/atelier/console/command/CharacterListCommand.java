package com.rithsagea.atelier.console.command;

import java.util.UUID;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Message;
import com.rithsagea.atelier.console.ModularCommand;

public class CharacterListCommand extends ModularCommand {

	public CharacterListCommand(AtelierBot bot) {
		super(bot);
	}

	@Override
	public String getLabel() {
		return "list";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public void executeDefault(Message message, String[] args) {
		for(UUID id : getBot().getCharacterDatabase().getIds()) {
			getBot().getConsole().sendMessage(id.toString());
		}
		
	}

}
