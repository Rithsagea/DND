package com.rithsagea.atelier.console;

public class TextConsole extends Console {

	@Override
	public void sendMessage(Message message) {
		System.out.println(message.getText());
	}

}
