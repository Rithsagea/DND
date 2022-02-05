package com.rithsagea.atelier.console.command;

import org.bson.types.ObjectId;

import com.rithsagea.atelier.AtelierBot;
import com.rithsagea.atelier.console.Command;
import com.rithsagea.atelier.console.Message;

import api.rithsagea.atelier.AtelierDatabase;
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
		ObjectId id = new ObjectId(args[1]);
		
		AtelierBot bot = getBot();
		AtelierDatabase db = bot.getDatabase();
		
		CharacterSheet sheet = db.findSheet(id);
		User user = db.findUser(message.getSender());
		
		user.setCharacterId(sheet.getId());
		db.updateUser(user);
		
		bot.getConsole().sendMessage("Selected Character: " + sheet.getId());
	}
	
}
