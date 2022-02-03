package com.rithsagea.atelier.console;

public interface Command {
	public String getLabel();
	public String[] getAliases();
	
	public void execute(Message message, String[] args);
}
