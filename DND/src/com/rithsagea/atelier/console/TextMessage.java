package com.rithsagea.atelier.console;

public class TextMessage implements Message {

	private String text;
	private long sender;
	
	public TextMessage(String text, long sender) {
		this.text = text;
		this.sender = sender;
	}
	
	public TextMessage(String text) {
		this(text, 0);
	}
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public long getSender() {
		return sender;
	}

}
