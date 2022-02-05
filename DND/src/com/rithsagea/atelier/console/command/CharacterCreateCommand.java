package com.rithsagea.atelier.console.command;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Command;
import com.rithsagea.atelier.console.Message;

import api.rithsagea.atelier.CharacterSheet;

public class CharacterCreateCommand extends Command {

	public CharacterCreateCommand(AtelierBot bot) {
		super(bot);
	}

	@Override
	public String getLabel() {
		return "create";
	}

	@Override
	public String[] getAliases() {
		return new String[] {"new"};
	}

	@Override
	public void execute(Message message, String[] args) {
		CharacterSheet sheet = getBot().getCharacterDatabase().newSheet();
		getBot().getConsole().sendMessage("Creating New Character: " + sheet.getId());
	}
	
}
