package com.rithsagea.atelier.console.command;

import java.util.UUID;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Command;
import com.rithsagea.atelier.console.Message;

import api.rithsagea.atelier.CharacterSheet;
import api.rithsagea.atelier.User;

public class CharacterSelectCommand extends Command {

	public CharacterSelectCommand(AtelierBot bot) {
		super(bot);
	}

	@Override
	public String getLabel() {
		return "select";
	}

	@Override
	public String[] getAliases() {
		return new String[] {"sel"};
	}

	@Override
	public void execute(Message message, String[] args) {
		UUID id = UUID.fromString(args[1]);
		
		CharacterSheet sheet = getBot().getCharacterDatabase().getSheet(id);
		User user = getBot().getUserDatabase().findUser(message.getSender());
		
		user.setCharacterId(sheet.getId());
		
		getBot().getConsole().sendMessage("Selected Character: " + sheet.getId());
	}
	
}
