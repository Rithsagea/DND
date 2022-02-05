package com.rithsagea.atelier.console.command;

import java.util.UUID;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Message;
import com.rithsagea.atelier.console.ModularCommand;
import com.rithsagea.atelier.console.TextMessage;

import api.rithsagea.atelier.CharacterSheet;
import api.rithsagea.atelier.User;

public class CharacterCommand extends ModularCommand {
	
	public CharacterCommand(AtelierBot bot) {
		super(bot);
		
		registerSubCommand(new CharacterCreateCommand(bot));
		registerSubCommand(new CharacterListCommand(bot));
		registerSubCommand(new CharacterSelectCommand(bot));
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
	public void executeDefault(Message message, String[] args) {
		AtelierBot bot = getBot();
		User user = bot.getUserDatabase().findUser(message.getSender());
		UUID id = user.getCharacterId();
		if(id == null) {
			bot.getConsole().sendMessage(new TextMessage("No Character Found!"));
		} else {
			CharacterSheet sheet = bot.getCharacterDatabase().getSheet(id);
			bot.getConsole().sendMessage(new TextMessage("" + sheet));
		}
	}
}
