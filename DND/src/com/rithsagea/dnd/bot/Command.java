package com.rithsagea.dnd.bot;

public interface Command {
	public String getLabel();
	public String[] getAliases();
	
	public void execute(String[] args);
}
