package com.rithsagea.atelier.console.command;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Message;
import com.rithsagea.atelier.console.ModularCommand;

import api.rithsagea.atelier.CharacterSheet;

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
		for(CharacterSheet sheet : getBot().getDatabase().getSheets()) {
			getBot().getConsole().sendMessage(sheet.getId().toString());
		}
	}

}
